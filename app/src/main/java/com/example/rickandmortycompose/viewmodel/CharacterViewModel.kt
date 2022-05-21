package com.example.rickandmortycompose.viewmodel

import android.location.Location
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.other.Constans.Companion.TAG
import com.example.rickandmortycompose.repositories.CharacterRepository
import com.example.rickandmortycompose.retrofit.characters.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {
   private var _listOfCharactersInEpisodeOrLocation = MutableStateFlow<MutableList<Character>>(mutableStateListOf<Character>())
     val listOfCharactersInEpisodeOrLocation = _listOfCharactersInEpisodeOrLocation //List for Episode Details
   private var _listOfAllCharacters = MutableStateFlow<MutableList<Character>> (mutableStateListOf<Character>() )
    val listOfAllCharacters = _listOfAllCharacters.asStateFlow()

   private var _filteredlistOfCharacters = MutableStateFlow<MutableList<Character>>(mutableStateListOf<Character>())
    var filteredlistOfCharacters = _filteredlistOfCharacters

    init {
        viewModelScope.launch {

                 characterRepository.getAllCharacters().collect {
                     _listOfAllCharacters.value.addAll(it)
                 }


        }


    }

    suspend fun getCurrentCharacters(numbers: String?){

      characterRepository.getCurrentCharacters(numbers).collect{
          if (it != null) {
              listOfCharactersInEpisodeOrLocation.value = it as MutableList<Character>
          }

      }

    }



    fun searchCharacter(name: String, list: MutableList<Character>) {
        Log.d(TAG, "without filter: ${list.size}")
        list.filter {
            it.name.lowercase().contains(name)
        }.let { filtered -> filteredlistOfCharacters.value = filtered.toMutableList() }
        Log.d(TAG, "with filter: ${filteredlistOfCharacters.value.size}")
    }

}









