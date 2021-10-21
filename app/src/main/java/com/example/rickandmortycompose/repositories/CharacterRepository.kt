package com.example.rickandmortycompose.repositories

import com.example.rickandmortycompose.retrofit.CharacterService
import com.example.rickandmortycompose.retrofit.characters.Character
import com.example.rickandmortycompose.retrofit.characters.CharacterList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CharacterRepository @Inject constructor(private val characterService: CharacterService) {


    fun getAllCharacters(number: Int): Observable<List<Character>>? {

        val data: Observable<List<Character>>? =
            characterService.getCharacters("/api/character/?page=${number}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(CharacterList::results)
                .flatMapIterable { listOf(it) }


        return data

    }

    fun getCurrentCharacters(numbers: String?): Observable<List<Character>>? {

        val data =
            characterService.getCurrentCharacters("/api/character/${numbers}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { listOf(it) }



        return data


    }
}
