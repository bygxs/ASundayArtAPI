package com.biniyam.asundayartapi;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.biniyam.asundayartapi.adapter.ArtworkAdapter;
import com.biniyam.asundayartapi.api.ArticApiService;
import com.biniyam.asundayartapi.api.RetrofitClient;
import com.biniyam.asundayartapi.response.ArtworkData;
import com.biniyam.asundayartapi.response.ArtworkListResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.artic.edu/";
    private RecyclerView recyclerView;
    private ArtworkAdapter artworkAdapter;
    private List<ArtworkData> artworkList;

    //private EditText searchEditText;
    //private ImageView searchButton;

    private ArticApiService apiService;
    private String currentQuery = ""; // Stores the current search query

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //searchEditText = findViewById(R.id.searchEditText);
        //searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.recyclerView);

        artworkList = new ArrayList<>();
        artworkAdapter = new ArtworkAdapter(artworkList, this);
        recyclerView.setAdapter(artworkAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the API service
        apiService = RetrofitClient.getClient(BASE_URL).create(ArticApiService.class);

        // Fetch and display random artworks on app start
        fetchArtworkList(99);

        /*
        // Set click listener on the search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchEditText.getText().toString().trim();
                //performSearch(query);
            }
        });
        */
    }

    private void fetchArtworkList(int limit) {
        Call<ArtworkListResponse> call = apiService.getArtworkList(limit, null);
        call.enqueue(new Callback<ArtworkListResponse>() {
            @Override
            public void onResponse(Call<ArtworkListResponse> call, Response<ArtworkListResponse> response) {
                if (response.isSuccessful()) {
                    List<ArtworkData> artworks = response.body().getData();
                    List<ArtworkData> randomArtworks = getRandomArtworks(artworks, limit);
                    artworkList.clear();
                    artworkList.addAll(randomArtworks);
                    artworkAdapter.notifyDataSetChanged();
                } else {
                    // Handle API call error
                }
            }

            @Override
            public void onFailure(Call<ArtworkListResponse> call, Throwable t) {
                // Handle API call failure
            }
        });
    }

    private List<ArtworkData> getRandomArtworks(List<ArtworkData> artworkList, int limit) {
        List<ArtworkData> randomArtworks = new ArrayList<>(artworkList);
        Collections.shuffle(randomArtworks);
        if (randomArtworks.size() > limit) {
            randomArtworks = randomArtworks.subList(0, limit);
        }
        return randomArtworks;
    }

}
