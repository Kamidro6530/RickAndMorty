package com.example.rickandmortycompose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rickandmortycompose.navigation.Routes
import com.example.rickandmortycompose.ui.theme.RickAndMortyComposeTheme
import com.example.rickandmortycompose.viewmodel.CharacterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EpisodeDetails(
    characters: String?,
    air_date: String?,
    created: String?,
    episode: String?,
    name: String?,
    characterViewModel: CharacterViewModel,
    navController: NavController
) {
        SideEffect {
            CoroutineScope(Dispatchers.IO).launch { characterViewModel.getCurrentCharacters(characters) }
        }


    RickAndMortyComposeTheme {


        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {

            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row {
                Text(
                    text = "$name",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.body2,
                    fontSize = 32.sp
                )

            }
            Spacer(modifier = Modifier.padding(10.dp))

            Row {

                Text(
                    text = "$episode",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.body2,
                    fontSize = 22.sp
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))


            Spacer(modifier = Modifier.padding(5.dp))
            Row {
                Text(
                    text = "Air Date: $air_date",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.body2,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Row {
                Text(
                    text = "Characters in episode : ",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.body2,
                    fontSize = 16.sp
                )
            }
            Row {
                Column {

                    Row {

                        LazyColumn(
                            Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            contentPadding = PaddingValues(bottom = 15.dp)
                        ) {
                            items(characterViewModel.listOfCharactersInEpisodeOrLocation.value) {
                                Text(
                                    text = it.name,
                                    color = MaterialTheme.colors.error,
                                    style = MaterialTheme.typography.body2,
                                    fontSize = 14.sp,
                                    modifier = Modifier.clickable {
                                        val image =
                                            it.image.replace(
                                                "/",
                                                "@"
                                            ) //Change char  to send it  as  argument (send string as url causes error)
                                        navController.navigate(
                                            Routes.CharacterDetails.withArgs(
                                                it.name,
                                                it.status,
                                                it.species,
                                                it.gender,
                                                it.origin.name,
                                                image
                                            )
                                        )
                                    }
                                )
                            }
                        }

                    }

                }
            }


        }
        Spacer(modifier = Modifier.padding(5.dp))


    }


}








