package com.example.rickandmortycompose.navigation

import androidx.compose.runtime.Composable


sealed class Routes (val route : String) {
    object ListCharacters : Routes("listCharacters")
    object CharacterDetails : Routes("characterDetails")
    object EpisodeDetails : Routes("episodeDetails")
    object ListEpisodes : Routes("listEpisodes")
    object ListLocation : Routes("listLocations")
    object LocationDetails : Routes("locationDetails")

    fun withArgs(vararg args: String?) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}

