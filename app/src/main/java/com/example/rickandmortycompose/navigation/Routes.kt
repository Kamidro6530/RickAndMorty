package com.example.rickandmortycompose.navigation


sealed class Routes (val route : String) {
    object ListCharacters : Routes("listCharacters")
    object CharacterDetails : Routes("characterDetails")
    object EpisodeDetails : Routes("episodeDetails")
    object ListEpisodes : Routes("listEpisodes")

    fun withArgs(vararg args: String?) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    fun withArgsAndList( vararg args: String?) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}