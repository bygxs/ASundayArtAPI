package com.biniyam.asundayartapi.api;

import com.biniyam.asundayartapi.response.ArtworkListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;





public interface ArticApiService {
    @GET("api/v1/artworks")
    Call<ArtworkListResponse> getArtworkList(@Query("fields") String fields, @Query("limit") int limit);
}
/*
public interface ArticApiService {
    @GET("api/v1/artworks/{artworkId}")
    Call<ArtworkResponse> getArtwork(@Path("artworkId") int artworkId, @Query("fields") String fields);
}
*/