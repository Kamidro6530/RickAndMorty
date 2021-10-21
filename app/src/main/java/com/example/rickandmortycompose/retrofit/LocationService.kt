package com.example.rickandmortycompose.retrofit

import com.example.rickandmortycompose.retrofit.locations.LocationList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface LocationService {

    @GET
    fun getLocations(@Url url: String): Observable<LocationList>
}