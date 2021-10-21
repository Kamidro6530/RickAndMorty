package com.example.rickandmortycompose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rickandmortycompose.navigation.Routes
import com.example.rickandmortycompose.viewmodel.CharacterViewModel

@Composable
fun LocationDetails(
    name: String?,
    type: String?,
    dimension: String?,
    residents: String?,
    characterViewModel: CharacterViewModel,
    navController: NavHostController
) {

    characterViewModel.getCurrentCharacters(residents)

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
                text = name.toString(),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body2,
                fontSize = 32.sp
            )

        }
        Spacer(modifier = Modifier.padding(10.dp))

        Row {

            Text(
                text = type.toString(),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body2,
                fontSize = 22.sp
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))


        Spacer(modifier = Modifier.padding(5.dp))
        Row {
            Text(
                text = "Dimension: $dimension",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body2,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Row {
            Text(
                text = "Residents in location : ",
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
                        horizontalAlignment = Alignment.CenterHorizontally
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
                                        )//Change char  to send it  as  argument (send string as url causes error)
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


