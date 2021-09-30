package com.example.rickandmortycompose.screens

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rickandmortycompose.other.Constans.Companion.TAG
import com.example.rickandmortycompose.retrofit.episodes.Episode
import com.example.rickandmortycompose.viewmodel.CharacterViewModel
import com.example.rickandmortycompose.viewmodel.EpisodeViewModel

@Composable
fun ListEpisodes(episodeViewModel: EpisodeViewModel = viewModel(),characterViewModel: CharacterViewModel = viewModel(), navController: NavController) {
    var listOfEpisodes: MutableList<Episode?> = mutableListOf()
    var list: List<Episode>? = listOf()




    for (x in 1..3) {

        list = episodeViewModel.listOfEpisodes.value[x]
        list?.forEach {
            listOfEpisodes.add(it)

        }

    }

    episodeViewModel.listOfEpisodesMutable.value//I don't know If i delete this line App no display characters WTF !!!!


    if (list != null) {
        ListOfEpisodes(list = listOfEpisodes, navController,characterViewModel)
    }


}

@Composable
fun ListOfEpisodes(
    list: MutableList<Episode?>,
    navController: NavController,
    characterViewModel: CharacterViewModel
) {

    LazyColumn(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (x in 1..4) {
            item { ListClassifier(text = "Season $x") }
            items(list) {
                if (it?.episode.toString().contains("S0$x")) {
                    ItemEpisodeList(it, navController,characterViewModel)
                }

            }
        }

    }

}

@Composable
fun ItemEpisodeList(episode: Episode?, navController: NavController,characterViewModel: CharacterViewModel) {




    var characterNumbers = StringBuilder()
        //Wydziela numer postaci z adresu url aby móc wysłać go do EpisodeDetail
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
                characterViewModel.listOfCharactersInEpisode.value.clear()
                navController.navigate(
                    Routes.EpisodeDetails.withArgsAndList(
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