package com.example.rickandmortycompose.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortycompose.screens.CharacterScreen
import com.example.rickandmortycompose.screens.EpisodeDetails
import com.example.rickandmortycompose.screens.ListCharacters
import com.example.rickandmortycompose.screens.ListEpisodes
import com.example.rickandmortycompose.ui.theme.RickAndMortyComposeTheme
import com.example.rickandmortycompose.viewmodel.CharacterViewModel
import com.example.rickandmortycompose.viewmodel.EpisodeViewModel

@Composable
fun Navigation(characterViewModel : CharacterViewModel,episodeViewModel : EpisodeViewModel,navController: NavHostController) {

        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxSize()
        ) {

            NavHost(
                navController = navController,
                startDestination = Routes.ListEpisodes.route
            ) {
                composable(Routes.ListCharacters.route) {

                    ListCharacters(characterViewModel, navController)
                }
                composable(
                    route = Routes.CharacterDetails.route + "/{name}/{status}/{species}/{gender}/{origin}/{image}",
                    arguments = listOf(
                        navArgument("name") {},
                        navArgument("status") {},
                        navArgument("species") {},
                        navArgument("gender") {},
                        navArgument("origin") {},
                        navArgument("image") {}
                    )
                ) { entry ->
                    CharacterScreen(
                        name = entry.arguments?.getString("name"),
                        status = entry.arguments?.getString("status"),
                        species = entry.arguments?.getString("species"),
                        gender = entry.arguments?.getString("gender"),
                        origin = entry.arguments?.getString("origin"),
                        image = entry.arguments?.getString("image"),

                        )
                }

                composable(route = Routes.ListEpisodes.route) {
                    ListEpisodes(episodeViewModel, characterViewModel, navController)
                }

                composable(
                    Routes.EpisodeDetails.route + "/{characters}/{air_date}/{created}/{episode}/{name}",
                    arguments = listOf(
                        navArgument("characters") { },
                        navArgument("air_date") {},
                        navArgument("created") {},
                        navArgument("episode") {},
                        navArgument("name") {},
                    )
                ) { entry ->


                    EpisodeDetails(
                        characters = entry.arguments?.getString("characters"),
                        air_date = entry.arguments?.getString("air_date"),
                        created = entry.arguments?.getString("created"),
                        episode = entry.arguments?.getString("episode"),
                        name = entry.arguments?.getString("name"),
                        characterViewModel,
                        navController
                    )

                }
            }
        }

    }
