package com.example.rickandmortycompose.repositories

import com.example.rickandmortycompose.retrofit.EpisodeService
import com.example.rickandmortycompose.retrofit.characters.Character
import com.example.rickandmortycompose.retrofit.characters.CharacterList
import com.example.rickandmortycompose.retrofit.episodes.Episode
import com.example.rickandmortycompose.retrofit.episodes.EpisodeList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val episodeService: EpisodeService) {

    fun getEpisodes(number : Int): Observable<List<Episode>>? {

        var data: Observable<List<Episode>>? =
            episodeService.getEpisodes("/api/episode/?page=${number}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(EpisodeList::results)
                .flatMapIterable { it -> listOf(it) }


        return data

    }
}