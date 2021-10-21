package com.example.rickandmortycompose.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.repositories.EpisodeRepository
import com.example.rickandmortycompose.retrofit.episodes.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(private val episodeRepository: EpisodeRepository) :
    ViewModel() {

    var listOfEpisodes = mutableStateOf(arrayOfNulls<List<Episode>>(4))//List for Episode List
    var listOfEpisodesMutable: MutableState<List<Episode>> =
        mutableStateOf(mutableListOf())

    init {
        viewModelScope.launch {
            for (number in 1..3) {

                val list = episodeRepository.getEpisodes(number)
                list?.subscribe {
                    listOfEpisodes.value[number] = it
                    listOfEpisodesMutable.value =
                        it


                }


            }

        }
    }
}