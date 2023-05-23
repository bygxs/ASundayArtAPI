package com.biniyam.asundayartapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArticApiService {
    @GET("api/v1/artworks/{artworkId}")
    Call<ArtworkResponse> getArtwork(@Path("artworkId") int artworkId, @Query("fields") String fields);
}
