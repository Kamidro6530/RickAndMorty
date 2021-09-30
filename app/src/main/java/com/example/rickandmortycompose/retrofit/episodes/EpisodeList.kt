package com.example.rickandmortycompose.retrofit.episodes

data class EpisodeList(
    val info: Info,
    val results: List<Episode>
)