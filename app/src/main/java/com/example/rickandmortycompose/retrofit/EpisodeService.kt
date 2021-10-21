package com.example.rickandmortycompose.retrofit

import com.example.rickandmortycompose.retrofit.episodes.EpisodeList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface EpisodeService {

    @GET
    fun getEpisodes(@Url url: String): Observable<EpisodeList>
}