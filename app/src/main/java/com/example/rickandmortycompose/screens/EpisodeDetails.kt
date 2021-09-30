package com.example.rickandmortycompose.screens

import android.util.Log
import androidx.activity.OnBackPressedCallback
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
import androidx.navigation.NavController
import com.example.rickandmortycompose.other.Constans.Companion.TAG
import com.example.rickandmortycompose.retrofit.characters.Character
import com.example.rickandmortycompose.ui.theme.RickAndMortyComposeTheme
import com.example.rickandmortycompose.viewmodel.CharacterViewModel

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

    characterViewModel.getCurrentCharacters(characters)

    RickAndMortyComposeTheme() {



        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {

            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row() {
                Text(
                    text = "${name}",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.body2,
                    fontSize = 32.sp
                )

            }
            Spacer(modifier = Modifier.padding(10.dp))

            Row() {

                Text(
                    text = "$episode",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.body2,
                    fontSize = 22.sp
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Row() {
                Text(
                    text = "Created: $created",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.body2,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Row() {
                Text(
                    text = "Air Date: $air_date",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.body2,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Row() {
                Text(
                    text = "Characters in episode : ",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.body2,
                    fontSize = 16.sp
                )
            }
            Row() {
                Column() {

                            Row() {

                                     LazyColumn(Modifier.fillMaxSize(),
                                         horizontalAlignment = Alignment.CenterHorizontally){
                                         items(characterViewModel.listOfCharactersInEpisode.value){
                                             Text(
                                                 text = it.name,
                                                 color = MaterialTheme.colors.error,
                                                 style = MaterialTheme.typography.body2,
                                                 fontSize = 14.sp,
                                                 modifier = Modifier.clickable {
                                                     val image =
                                                         it.image.replace("/","@")//Zamienia znaki aby przesłać je jako argument (Przesyłanie string jako url powoduje błąd)
                                                     navController.navigate(Routes.CharacterDetails.withArgs(it.name,it.status,it.species,it.gender,it.origin.name,image))
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








