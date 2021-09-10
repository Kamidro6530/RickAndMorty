package com.example.rickandmortycompose.screens

import com.example.rickandmortycompose.retrofit.characters.Character
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


sealed class Routes (val route : String) {
    object ListCharacters : Routes("listCharacters")
    object CharacterDetails : Routes("characterDetails")

    fun withArgs(vararg args: String?) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}