package com.trenton.wmapp.service;

import com.trenton.wmapp.model.WmResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Single;

public interface WmService {

    @GET("walmartproducts/{api_key}/{page_number}/{page_size}")
    Single<WmResponse> getTVList(@Path("api_key") String apiKey, @Path("page_number") int pageNumber, @Path("page_size") int pageSize );

}
