package com.example.rickandmortycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortycompose.navigation.Navigation
import com.example.rickandmortycompose.navigation.Routes
import com.example.rickandmortycompose.screens.*
import com.example.rickandmortycompose.ui.theme.RickAndMortyComposeTheme
import com.example.rickandmortycompose.viewmodel.CharacterViewModel
import com.example.rickandmortycompose.viewmodel.EpisodeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val characterViewModel: CharacterViewModel by viewModels()

    val episodeViewModel: EpisodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        RickAndMortyComposeTheme() {
            val navController = rememberNavController()
            Scaffold(bottomBar = { BottomNavigationBar(navController = navController) }) {

                Navigation(characterViewModel = characterViewModel, episodeViewModel = episodeViewModel, navController = navController )
            }
        }

        }


    }


}