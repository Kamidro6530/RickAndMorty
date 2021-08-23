package com.example.rickandmortycompose.mvvm

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.Constans.Companion.TAG
import com.example.rickandmortycompose.retrofit.characters.Character
import com.example.rickandmortycompose.retrofit.characters.CharacterList
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RiMViewModel @Inject constructor(private val repository : RiMRepository) : ViewModel() {

    var listOfCharacters = mutableStateOf(arrayOfNulls<List<Character>>(33))
    var listOfCharactersTEST : MutableState<List<Character>> = mutableStateOf(mutableListOf())//I don't know If i delete this line App no display characters WTF !!!!

    init {
        viewModelScope.launch {
            for (number in 1..32) {

                val list = repository.getCharacters(number)
                list?.subscribe{ listOfCharacters.value[number] = it
                    listOfCharactersTEST.value = it//I don't know If i delete this line App no display characters WTF !!!!


                }



                }

            }
        }

        }



