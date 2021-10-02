package com.example.rickandmortycompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.rickandmortycompose.navigation.Routes
import com.example.rickandmortycompose.retrofit.characters.Character
import com.example.rickandmortycompose.viewmodel.CharacterViewModel


@Composable
    fun ListCharacters(viewModel: CharacterViewModel = viewModel(), navController: NavController) {
        var listOfCharacters: MutableList<Character?> = mutableListOf()
        var list: List<Character>? = listOf()



        for (x in 1..32) {
            list = viewModel.listOfAllCharacters.value[x]
            list?.forEach { listOfCharacters.add(it) }

        }

            viewModel.listOfAllCharactersMutable.value//I don't know If i delete this line App no display characters WTF !!!!


        if (list != null) {
            ListOfCharacters(list = listOfCharacters,navController)
        }


    }

    @Composable
    fun ListOfCharacters(list: MutableList<Character?>, navController: NavController) {

        LazyColumn(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            items(list) {
                ItemCharacterList(it,navController)
            }

        }

    }

    @Composable
    fun ItemCharacterList(character: Character?, navController: NavController) {


        Card(
            backgroundColor = MaterialTheme.colors.error,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .padding(top = 6.dp, bottom = 6.dp, start = 15.dp, end = 15.dp)
                .clickable {

                    var image = character?.image?.replace("/","@")//Zamienia znaki aby przesłać je jako argument (Przesyłanie string jako url powoduje błąd)

                    navController.navigate(Routes.CharacterDetails.withArgs(character?.name,character?.status,character?.species,character?.gender,character?.origin?.name,image))

                },

            elevation = 8.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row() {
                    Image(
                        painter = rememberImagePainter(character?.image),
                        "",
                        modifier = Modifier.size(250.dp),

                        )
                }
                Row(
                    Modifier.width(250.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        character?.name.toString(),
                        maxLines = 1,
                        color = MaterialTheme.colors.primaryVariant,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }





