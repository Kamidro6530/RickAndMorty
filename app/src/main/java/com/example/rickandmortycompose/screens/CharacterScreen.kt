package com.example.rickandmortycompose.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.rickandmortycompose.other.Constans
import com.example.rickandmortycompose.ui.theme.RickAndMortyComposeTheme

@Composable
    fun CharacterScreen(
    name: String?,
    status: String?,
    species: String?,
    gender: String?,
    origin: String?,
    image: String?,
) {
        RickAndMortyComposeTheme() {


            Log.d(Constans.TAG, "CharacterScreen: ${name}")
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row() {
                    Image(
                        painter = rememberImagePainter(image?.replace("@","/")),
                        contentDescription = null,
                        modifier = Modifier.size(300.dp),

                        )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row() {
                    Text(
                        text = "${name}",
                        color =  MaterialTheme.colors.error,
                        style = MaterialTheme.typography.body2,
                        fontSize = 32.sp
                    )

                }
                Spacer(modifier = Modifier.padding(10.dp))

                Row() {

                    var color = Color(1)
                    when (status) {
                        "Alive" -> {
                            color = Color.Green
                        }
                        "Dead" -> {
                            color = Color.Red
                        }
                        "unknown" -> {
                            color = Color.Gray
                        }
                    }
                    Text(
                        text = "$status",
                        color = color,
                        style = MaterialTheme.typography.body2,
                        fontSize = 22.sp
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))

                Row() {
                    Text(
                        text = "Species: $species",
                        color =  MaterialTheme.colors.error,
                        style = MaterialTheme.typography.body2,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Row() {
                    Text(
                        text = "Gender: $gender",
                        color =  MaterialTheme.colors.error,
                        style = MaterialTheme.typography.body2,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Row() {
                    Text(
                        text = "Origin: $origin",
                        color =  MaterialTheme.colors.error,
                        style = MaterialTheme.typography.body2,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))

            }

        }


    }


