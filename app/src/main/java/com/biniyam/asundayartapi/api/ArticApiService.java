package com.biniyam.asundayartapi.api;

import com.biniyam.asundayartapi.response.ArtworkListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;





public interface ArticApiService {

    // search
    @GET("api/v1/artworks")
    Call<ArtworkListResponse> getArtworkList(
            @Query("limit") int limit,
            @Query("query") String query
    );
}

    /*
        @GET("api/v1/artworks")
        Call<ArtworkListResponse> getArtworkList(
                @Query("limit") int limit,
                @Query("fields") String fields
        );





    /*
    @GET("api/v1/artworks")
    Call<ArtworkListResponse> getArtworkList(
            @Query("limit") int limit,
            @Query("page") int page,
            @Query("fields") String fields
    );




    /*
    //random pages request
    @GET("api/v1/artworks")
    Call<ArtworkListResponse> getArtworkList(@Query("fields") String fields, @Query("limit") int limit, @Query("page") int page);

    // standard image request, results in  same images everytime
    /*
    @GET("api/v1/artworks")
    Call<ArtworkListResponse> getArtworkList(@Query("fields") String fields, @Query("limit") int limit);

     */

/*
public interface ArticApiService {
    @GET("api/v1/artworks/{artworkId}")
    Call<ArtworkResponse> getArtwork(@Path("artworkId") int artworkId, @Query("fields") String fields);

*/