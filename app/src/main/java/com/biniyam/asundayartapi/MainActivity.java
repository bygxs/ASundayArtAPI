package com.biniyam.asundayartapi;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.artic.edu/";

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ArticApiService apiService = retrofit.create(ArticApiService.class);

        // Make the API call
        Call<ArtworkResponse> call = apiService.getArtwork(27992, "id,title,image_id");
        call.enqueue(new Callback<ArtworkResponse>() {
            @Override
            public void onResponse(Call<ArtworkResponse> call, Response<ArtworkResponse> response) {
                if (response.isSuccessful()) {
                    ArtworkResponse artworkResponse = response.body();
                    if (artworkResponse != null) {
                        ArtworkData artworkData = artworkResponse.getData();
                        ConfigData configData = artworkResponse.getConfig();

                        String iiifUrl = configData.getIiifUrl();
                        String imageId = artworkData.getImageId();

                        String imageUrl = iiifUrl + "/" + imageId + "/full/843,/0/default.jpg";

                        // Load the image into the ImageView using Glide
                        Glide.with(MainActivity.this)
                                .load(imageUrl)
                                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background))
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(imageView);

                        // Display the extracted values
                        Log.d(TAG, "Image URL: " + imageUrl);
                       // Log.d(TAG, "Title: " + title);
                    }
                } else {
                    // Handle API error
                    Log.e(TAG, "Response failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ArtworkResponse> call, Throwable t) {
                // Handle network or other errors
                Log.e(TAG, "API call failed: " + t.getMessage());
            }
        });
    }
}
