package com.example.rickandmortycompose.retrofit

import com.example.rickandmortycompose.retrofit.characters.CharacterList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface RiMService {

    @GET
    fun getCharacters(@Url url: String): Observable<CharacterList>
}