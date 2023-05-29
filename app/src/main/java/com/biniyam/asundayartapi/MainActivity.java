package com.biniyam.asundayartapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.biniyam.asundayartapi.adapter.ArtworkAdapter;
import com.bumptech.glide.Glide;
import com.biniyam.asundayartapi.api.ArticApiService;
import com.biniyam.asundayartapi.api.RetrofitClient;
import com.biniyam.asundayartapi.response.ArtworkData;
import com.biniyam.asundayartapi.response.ArtworkListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.artic.edu/";
    private RecyclerView recyclerView;
    private ArtworkAdapter artworkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArticApiService apiService = RetrofitClient.getClient(BASE_URL).create(ArticApiService.class);

        // Make the API call to fetch a list of artwork titles and image IDs
        Call<ArtworkListResponse> call = apiService.getArtworkList("title,image_id", 10); // Fetch 10 artworks with titles and image IDs
        call.enqueue(new Callback<ArtworkListResponse>() {
            @Override
            public void onResponse(Call<ArtworkListResponse> call, Response<ArtworkListResponse> response) {
                if (response.isSuccessful()) {
                    ArtworkListResponse artworkListResponse = response.body();
                    if (artworkListResponse != null) {
                        List<ArtworkData> artworkList = artworkListResponse.getData();

                        // Create the adapter and set it to the RecyclerView
                        artworkAdapter = new ArtworkAdapter(artworkList, MainActivity.this);
                        recyclerView.setAdapter(artworkAdapter);
                    }
                } else {
                    // Handle API error
                    Log.e("API Error", "Response failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ArtworkListResponse> call, Throwable t) {
                // Handle network or other errors
                Log.e("API Error", "API call failed: " + t.getMessage());
            }
        });
    }
}
