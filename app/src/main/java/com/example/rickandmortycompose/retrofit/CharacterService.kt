package com.example.rickandmortycompose.retrofit

import com.example.rickandmortycompose.retrofit.characters.Character
import com.example.rickandmortycompose.retrofit.characters.CharacterList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterService {

    @GET
    fun getCharacters(@Url url: String): Observable<CharacterList>

    @GET
    fun getCurrentCharacters(@Url url: String): Observable<List<Character>>

}