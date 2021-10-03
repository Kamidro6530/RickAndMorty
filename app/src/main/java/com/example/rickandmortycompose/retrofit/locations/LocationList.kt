package com.example.rickandmortycompose.retrofit.locations



data class LocationList(
    val info: Info,
    val results: List<Location>
)