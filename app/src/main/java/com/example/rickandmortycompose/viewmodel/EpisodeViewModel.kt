package com.example.rickandmortycompose.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.repositories.EpisodeRepository
import com.example.rickandmortycompose.retrofit.characters.Character
import com.example.rickandmortycompose.retrofit.episodes.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(private val episodeRepository: EpisodeRepository) :
    ViewModel() {

    private val _listOfEpisodes = MutableStateFlow<MutableList<Episode>>(mutableStateListOf<Episode>())//List for Episode List
    var listOfEpisodes = _listOfEpisodes


    init {
        viewModelScope.launch {
                 episodeRepository.getEpisodes().collect{
                    if (it != null) {
                        listOfEpisodes.value.addAll(it)

                    }



            }

        }
    }
}