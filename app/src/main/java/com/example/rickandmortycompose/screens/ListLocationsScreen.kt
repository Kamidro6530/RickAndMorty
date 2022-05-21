package com.example.rickandmortycompose.screens


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
import com.example.rickandmortycompose.retrofit.locations.Location
import com.example.rickandmortycompose.viewmodel.CharacterViewModel
import com.example.rickandmortycompose.viewmodel.LocationViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun Locations(
    locationViewModel: LocationViewModel = viewModel(),
    navController: NavHostController,
    characterViewModel: CharacterViewModel
) {

    val listOfLocations: StateFlow<MutableList<Location>> = locationViewModel.listOfLocations.asStateFlow()





    ListOfLocations(list = listOfLocations,navController,characterViewModel)


}


@Composable
fun ListOfLocations(
    list: StateFlow<MutableList<Location>>,
    navController: NavHostController,
    characterViewModel: CharacterViewModel
) {

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(list.value) {
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
    //Separate number of character  from url adress  to can send it to  EpisodeDetail
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
            Row {
                Text(
                    text = location?.name.toString(),
                    Modifier.padding(10.dp),
                    color = MaterialTheme.colors.primaryVariant
                )

            }
        }
    }

}