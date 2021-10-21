package com.example.rickandmortycompose.navigation

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.navArgument
import com.example.rickandmortycompose.other.Constans.Companion.TAG
import com.example.rickandmortycompose.screens.*
import com.example.rickandmortycompose.viewmodel.CharacterViewModel
import com.example.rickandmortycompose.viewmodel.EpisodeViewModel
import com.example.rickandmortycompose.viewmodel.LocationViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@Composable
fun Navigation(
    characterViewModel: CharacterViewModel,
    episodeViewModel: EpisodeViewModel,
    locationViewModel: LocationViewModel,
    navController: NavHostController
) {
    var destination = 0 //Value 1 mean Characters value 0 mean Locations
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxSize()
    ) {

        AnimatedNavHost(
            navController = navController,
            startDestination = Routes.ListCharacters.route
        ) {

            composable(
                Routes.ListCharacters.route,
                exitTransition = { _, _ ->
                    slideOutHorizontally(
                        targetOffsetX = { +800 },
                        animationSpec = tween(
                            durationMillis = 800,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(800))
                },
                popEnterTransition = { initial, _ ->
                    slideInHorizontally(
                        initialOffsetX = { +800 },
                        animationSpec = tween(
                            durationMillis = 800,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(800))
                },
            ) {
                destination = 1
                Characters(characterViewModel, navController)
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


            composable(
                route = Routes.ListEpisodes.route,

                exitTransition = { _, _ ->
                    slideOutHorizontally(
                        targetOffsetX = {
                            if (destination == 1) {
                                -800
                            } else {
                                +800
                            }
                        },
                        animationSpec = tween(
                            durationMillis = 800,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(800))
                },
                popEnterTransition = { initial, _ ->
                    slideInHorizontally(
                        initialOffsetX = {
                            if (destination == 1) {
                                -800
                            } else {
                                +800
                            }
                        },
                        animationSpec = tween(
                            durationMillis = 800,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(800))
                },
            ) {
                Episodes(episodeViewModel, characterViewModel, navController)
                Log.d(TAG, "Navigation: ${navController.currentDestination}")
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
            composable(
                Routes.ListLocation.route,
                exitTransition = { _, _ ->
                    slideOutHorizontally(
                        targetOffsetX = { -800 },
                        animationSpec = tween(
                            durationMillis = 800,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(800))
                },
                popEnterTransition = { initial, _ ->
                    slideInHorizontally(
                        initialOffsetX = { -800 },
                        animationSpec = tween(
                            durationMillis = 800,
                            easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(800))
                },
            ) {
                destination = 0
                Locations(locationViewModel, navController, characterViewModel)
            }


            composable(Routes.LocationDetails.route + "/{name}/{type}/{dimension}/{residents}",
                arguments = listOf(
                    navArgument("name") {},
                    navArgument("type") {},
                    navArgument("dimension") {},
                    navArgument("residents") {}
                )
            ) { entry ->

                LocationDetails(
                    name = entry.arguments?.getString("name"),
                    type = entry.arguments?.getString("type"),
                    dimension = entry.arguments?.getString("dimension"),
                    residents = entry.arguments?.getString("residents"),
                    characterViewModel = characterViewModel,
                    navController = navController
                )
            }
        }
    }

}
