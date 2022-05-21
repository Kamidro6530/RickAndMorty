package com.example.rickandmortycompose.repositories

import android.util.Log
import com.example.rickandmortycompose.retrofit.CharacterService
import com.example.rickandmortycompose.retrofit.characters.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


class CharacterRepository @Inject constructor(private val characterService: CharacterService) {

    suspend fun getAllCharacters(): Flow<List<Character>> {

            return flow {

                    for (number in 1..32) {
                        val data = characterService.getCharacters("/api/character/?page=${number}")
                        emit(data.body()!!.results)
                    }

            }.flowOn(Dispatchers.Default)

    }


   suspend fun getCurrentCharacters(numbers: String?): Flow<List<Character>?> {

       return flow {
           val data2 = characterService.getCurrentCharacters("/api/character/${numbers}")
                   emit(data2.body())

       }.flowOn(Dispatchers.IO)





   }

}
