package com.example.rickandmortycompose.repositories

import com.example.rickandmortycompose.retrofit.EpisodeService
import com.example.rickandmortycompose.retrofit.episodes.Episode
import com.example.rickandmortycompose.retrofit.episodes.EpisodeList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val episodeService: EpisodeService) {

   suspend fun getEpisodes(): Flow<List<Episode>?> {
        return flow {

                for (number in 1..4) {
                    val data = episodeService.getEpisodes("/api/episode/?page=${number}")
                    emit(data.body()?.results)
                }



        }.flowOn(Dispatchers.IO)





    }
}