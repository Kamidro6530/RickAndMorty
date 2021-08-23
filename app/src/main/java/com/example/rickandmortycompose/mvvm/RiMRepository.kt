package com.example.rickandmortycompose.mvvm

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.rickandmortycompose.Constans.Companion.TAG
import com.example.rickandmortycompose.retrofit.RiMService
import com.example.rickandmortycompose.retrofit.characters.Character
import com.example.rickandmortycompose.retrofit.characters.CharacterList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class RiMRepository @Inject constructor(private val retrofit: RiMService) {


        fun getCharacters(number : Int): Observable<List<Character>>? {



            var data: Observable<List<Character>>? =
                retrofit.getCharacters("/api/character/?page=${number}")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(CharacterList::results)
                    .flatMapIterable { it -> listOf(it) }


            return data

        }
    }
