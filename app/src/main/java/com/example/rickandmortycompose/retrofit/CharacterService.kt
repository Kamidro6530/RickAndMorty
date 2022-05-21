package com.example.rickandmortycompose.retrofit

import com.example.rickandmortycompose.retrofit.characters.Character
import com.example.rickandmortycompose.retrofit.characters.CharacterList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterService {

    @GET
    suspend fun getCharacters(@Url url: String): Response<CharacterList>

    @GET
   suspend fun getCurrentCharacters(@Url url: String): Response<List<Character>>

}