package com.example.rickandmortycompose.screens


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.rickandmortycompose.navigation.Routes
import com.example.rickandmortycompose.other.Constans.Companion.TAG
import com.example.rickandmortycompose.retrofit.locations.Location
import com.example.rickandmortycompose.viewmodel.CharacterViewModel
import com.example.rickandmortycompose.viewmodel.LocationViewModel

@Composable
fun Locations(
    locationViewModel: LocationViewModel = viewModel(),
    navController: NavHostController,
    characterViewModel: CharacterViewModel
) {

    val listOfLocations: MutableList<Location?> = mutableListOf()
    var list: List<Location>? = listOf()

    for (x in 1..6) {

        list = locationViewModel.listOfLocations.value[x]
        list?.forEach {
            listOfLocations.add(it)

        }

    }

    locationViewModel.listOfLocationsMutable.value//I don't know If i delete this line App no display characters WTF !!!!


    if (list != null) {
        ListOfLocations(
            list = listOfLocations,
            navController,
            locationViewModel,
            characterViewModel
        )
    }
}


@Composable
fun ListOfLocations(
    list: MutableList<Location?>,
    navController: NavHostController,
    locationViewModel: LocationViewModel,
    characterViewModel: CharacterViewModel
) {

    LazyColumn(
        Modifier.fillMaxSize().padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(list) {
            ItemLocationScreen(location = it, navController, characterViewModel)
        }

    }


}

@Composable
fun ItemLocationScreen(
    location: Location?,
    navController: NavHostController,
    characterViewModel: CharacterViewModel
) {


    val residentNumbers = StringBuilder()
    //Wydziela numer postaci z adresu url aby móc wysłać go do EpisodeDetail
    location?.residents?.forEach {
        val residentNumber = it.split("/")
        residentNumbers.append("${residentNumber[5]},")

    }

    Card(
        backgroundColor = MaterialTheme.colors.error,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(top = 6.dp, bottom = 6.dp, start = 60.dp, end = 60.dp)
            .fillMaxWidth()
            .clickable {
                characterViewModel.listOfCharactersInEpisodeOrLocation.value.clear()
                navController.navigate(
                    Routes.LocationDetails.withArgs(
                        location?.name,
                        location?.type,
                        location?.dimension,
                        residentNumbers.toString()
                    )
                )
                residentNumbers.clear()
            },

        elevation = 8.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Text(
                    text = location?.name.toString(),
                    Modifier.padding(10.dp),
                    color = MaterialTheme.colors.primaryVariant
                )

            }
        }
    }

}