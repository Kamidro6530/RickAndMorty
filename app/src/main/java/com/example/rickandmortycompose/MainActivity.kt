package com.example.rickandmortycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortycompose.navigation.Navigation
import com.example.rickandmortycompose.ui.theme.RickAndMortyComposeTheme
import com.example.rickandmortycompose.viewmodel.CharacterViewModel
import com.example.rickandmortycompose.viewmodel.EpisodeViewModel
import com.example.rickandmortycompose.viewmodel.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val characterViewModel: CharacterViewModel by viewModels()

    val episodeViewModel: EpisodeViewModel by viewModels()

    val locationViewModel: LocationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyComposeTheme() {
                val navController = rememberNavController()
                Scaffold(bottomBar = { BottomNavigationBar(navController = navController) }) {
                    Navigation(
                        characterViewModel = characterViewModel,
                        episodeViewModel = episodeViewModel,
                        locationViewModel = locationViewModel,
                        navController = navController
                    )
                }
            }

        }


    }


}