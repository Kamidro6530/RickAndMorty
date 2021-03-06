package com.example.rickandmortycompose.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rickandmortycompose.navigation.Routes
import com.example.rickandmortycompose.retrofit.episodes.Episode
import com.example.rickandmortycompose.viewmodel.CharacterViewModel
import com.example.rickandmortycompose.viewmodel.EpisodeViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun Episodes(
    episodeViewModel: EpisodeViewModel = viewModel(),
    characterViewModel: CharacterViewModel = viewModel(),
    navController: NavController
) {

    val listOfEpisodes = episodeViewModel.listOfEpisodes.asStateFlow()

        ListOfEpisodes(list = listOfEpisodes, navController, characterViewModel)



}

@Composable
fun ListOfEpisodes(
    list: StateFlow<MutableList<Episode>>,
    navController: NavController,
    characterViewModel: CharacterViewModel,

    ) {

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (x in 1..4) {
            item { ListClassifier(text = "Season $x") }
            items(list.value) {
                if (it.episode.contains("S0$x")) {
                    ItemEpisodeList(it, navController, characterViewModel)
                }

            }
        }

    }

}

@Composable
fun ItemEpisodeList(
    episode: Episode?,
    navController: NavController,
    characterViewModel: CharacterViewModel
) {

    val characterNumbers = StringBuilder()
    //Separate number of character  from url adress  to can send it to  EpisodeDetail
    episode?.characters?.forEach {
        val characterNumber = it.split("/")
        characterNumbers.append("${characterNumber[5]},")

    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.error)
            .clickable {
                characterViewModel.listOfCharactersInEpisodeOrLocation.value.clear()
                navController.navigate(
                    Routes.EpisodeDetails.withArgs(
                        characterNumbers.toString(),
                        episode?.air_date,
                        episode?.created,
                        episode?.episode,
                        episode?.name,
                    )
                )
            }

    ) {

        Text(
            text = "${episode?.episode}",
            Modifier.padding(10.dp),
            color = MaterialTheme.colors.primaryVariant
        )
    }
}

@Composable
fun ListClassifier(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(2.dp, MaterialTheme.colors.error)),
        contentAlignment = Alignment.Center

    ) {

        Text(text = text, Modifier.padding(10.dp), color = MaterialTheme.colors.background)
    }
}