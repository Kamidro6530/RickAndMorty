package com.example.rickandmortycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortycompose.mvvm.RiMViewModel
import com.example.rickandmortycompose.screens.CharacterScreen
import com.example.rickandmortycompose.screens.ListCharacters
import com.example.rickandmortycompose.screens.Routes
import com.example.rickandmortycompose.ui.theme.RickAndMortyComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {




    val viewModel: RiMViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyComposeTheme() {
                Surface(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.ListCharacters.route
                    ) {
                        composable(Routes.ListCharacters.route) {

                            ListCharacters(viewModel, navController)
                        }
                        composable(
                            route = Routes.CharacterDetails.route + "/{name}/{status}/{species}/{gender}/{origin}/{image}",arguments = listOf(
                                navArgument("name"){},
                                navArgument("status"){},
                                navArgument("species"){},
                                navArgument("gender"){},
                                navArgument("origin"){},
                                navArgument("image"){}
                            )
                        ) { entry ->
                            CharacterScreen(
                               name =  entry.arguments?.getString("name"),
                                status = entry.arguments?.getString("status"),
                                species = entry.arguments?.getString("species"),
                                gender =    entry.arguments?.getString("gender"),
                                origin =  entry.arguments?.getString("origin"),
                                image = entry.arguments?.getString("image")
                            )
                        }

                    }
                }

            }

        }


    }



}