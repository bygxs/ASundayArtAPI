package com.biniyam.asundayartapi;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.biniyam.asundayartapi.response.ArtworkData;
import com.biniyam.asundayartapi.response.ArtworkListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.artic.edu/";
    private ListView listView;
    private ArrayAdapter<String> artworkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ArticApiService apiService = retrofit.create(ArticApiService.class);

        // Make the API call to fetch a list of artwork titles and image IDs
        Call<ArtworkListResponse> call = apiService.getArtworkList("title,image_id", 5); // Fetch 10 artworks with titles and image IDs
        call.enqueue(new Callback<ArtworkListResponse>() {
            @Override
            public void onResponse(Call<ArtworkListResponse> call, Response<ArtworkListResponse> response) {
                if (response.isSuccessful()) {
                    ArtworkListResponse artworkListResponse = response.body();
                    if (artworkListResponse != null) {
                        List<ArtworkData> artworkList = artworkListResponse.getData();

                        // Extract the titles, image IDs, and construct IIIF URLs
                        List<String> artworks = new ArrayList<>();
                        for (ArtworkData artwork : artworkList) {
                            String title = artwork.getTitle();
                            String imageId = artwork.getImageId();
                            String iiifUrl = "https://www.artic.edu/iiif/2/" + imageId + "/full/843,/0/default.jpg";
                            artworks.add(title + "\n" + iiifUrl);
                            System.out.println((title + "\n" + iiifUrl));
                        }

                        // Create the adapter and set it to the ListView
                        artworkAdapter = new ArrayAdapter<>(MainActivity.this,
                                android.R.layout.simple_list_item_1, artworks);
                        listView.setAdapter(artworkAdapter);
                    }
                } else {
                    // Handle API error
                    Log.e(TAG, "Response failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ArtworkListResponse> call, Throwable t) {
                // Handle network or other errors
                Log.e(TAG, "API call failed: " + t.getMessage());
            }
        });
    }
}
