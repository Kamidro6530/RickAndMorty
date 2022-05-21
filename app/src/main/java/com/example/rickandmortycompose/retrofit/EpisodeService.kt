package com.example.rickandmortycompose.retrofit

import com.example.rickandmortycompose.retrofit.episodes.EpisodeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface EpisodeService {

    @GET
    suspend fun getEpisodes(@Url url: String): Response<EpisodeList>
}