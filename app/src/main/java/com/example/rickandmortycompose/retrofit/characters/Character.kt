package com.example.rickandmortycompose.retrofit.characters

import com.google.gson.annotations.SerializedName

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    @SerializedName("image")
    val image: String,
    val location: Location,
    @SerializedName("name")
    val name: String,
    val origin: Origin,
    val species: String,
    @SerializedName("status")
    val status: String,
    val type: String,
    val url: String
)