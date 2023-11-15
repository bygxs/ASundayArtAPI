package com.biniyam.asundayartapi.api;

import com.biniyam.asundayartapi.response.ArtworkListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ArticApiService {

    // Fetch all artworks
    @GET("api/v1/artworks")
    Call<ArtworkListResponse> getArtworkList(
            @Query("limit") int limit
    );

    // Search artworks by query
    @GET("api/v1/artworks/search")
    Call<ArtworkListResponse> searchArtworks(
            @Query("q") String query,
            @Query("fields") String fields
    );
}


/*
    // Get artwork list
    @GET("api/v1/artworks")
    Call<ArtworkListResponse> getArtworkList(
            @Query("limit") int limit,
            @Query("page") int page
          //  @Query("fields") String fields
           // @Query("include") String include
    );

    // Search artworks
    @GET("api/v1/artworks/search")
    Call<ArtworkListResponse> searchArtworks(
            @Query("q") String query,
            @Query("sort") String sort,
            @Query("from") String from,
           // @Query("size") int size,
            @Query("limit") int limit
           // @Query("facets") String facets
    );
}






/*
    @GET("api/v1/artworks")
    Call<ArtworkListResponse> getArtworkList(
            @Query("limit") int limit,
            @Query("page") int page,
            @Query("fields") String fields,
            @Query("include") String include
    );

    @GET("api/v1/artworks/search")
    Call<ArtworkListResponse> searchArtworks(
            @Query("q") String query,
            @Query("query") String complexQuery,
            @Query("sort") String sort,
            @Query("from") int from,
            @Query("size") int size,
            @Query("facets") String facets
    );

    @GET("api/v1/artworks/{id}")
    Call<ArtworkListResponse> getArtwork(
            @Path("id") int id
    );

    @GET("api/v1/artworks/{id}/manifest.json")
    Call<ManifestResponse> getArtworkManifest(
            @Path("id") int id
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