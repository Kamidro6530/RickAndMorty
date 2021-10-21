package com.example.rickandmortycompose.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.repositories.CharacterRepository
import com.example.rickandmortycompose.retrofit.characters.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {
    var listOfCharactersInEpisodeOrLocation: MutableState<MutableList<Character>> =
        mutableStateOf(mutableListOf())//List for Episode Details
    var listOfAllCharacters =
        mutableStateOf(arrayOfNulls<List<Character>>(33))//List for Character List
    var listOfAllCharactersMutable: MutableState<List<Character>> =
        mutableStateOf(mutableListOf())

    var filteredlistOfCharacters: MutableState<MutableList<Character>> =
        mutableStateOf(mutableListOf())//List of filtered characters

    init {
        viewModelScope.launch {
            for (number in 1..32) {

                val list = characterRepository.getAllCharacters(number)
                list?.subscribe {
                    listOfAllCharacters.value[number] = it
                    listOfAllCharactersMutable.value =
                        it
                }
            }
        }


    }

    fun getCurrentCharacters(numbers: String?) {

        val list = characterRepository.getCurrentCharacters(numbers)
            ?.subscribe {
                listOfCharactersInEpisodeOrLocation.value = it.toMutableList()
            }
    }

    fun searchCharacter(name: String, list: List<Character>) {
        list.filter {
            it.name.lowercase().contains(name)
        }.let { filtered -> filteredlistOfCharacters.value = filtered.toMutableList() }
    }

}







