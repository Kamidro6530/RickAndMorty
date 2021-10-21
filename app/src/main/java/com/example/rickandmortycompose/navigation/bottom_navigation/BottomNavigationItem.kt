package com.example.rickandmortycompose.navigation.bottom_navigation

import com.example.rickandmortycompose.R

sealed class BottomNavigationItem(
    val name: String,
    val icon: Int,
    val routes: String
) {
    object ListCharacters :
        BottomNavigationItem("Characters", R.drawable.ic_characters, "listCharacters")

    object ListEpisodes : BottomNavigationItem("Episodes", R.drawable.ic_episodes, "listEpisodes")

    object ListLocations :
        BottomNavigationItem("Locations", R.drawable.ic_location, "listLocations")
}
