package com.example.rickandmortycompose.viewmodel


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.repositories.LocationRepository
import com.example.rickandmortycompose.retrofit.locations.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val locationRepository: LocationRepository) :
    ViewModel() {

    var listOfLocations = mutableStateOf(arrayOfNulls<List<Location>>(7))
    var listOfLocationsMutable: MutableState<List<Location>> =
        mutableStateOf(mutableListOf())//I don't know If i delete this line App no display characters WTF !!!!

    init {
        viewModelScope.launch {
            for (number in 1..6) {

                val list = locationRepository.getLocations(number)
                list?.subscribe {
                    listOfLocations.value[number] = it
                    listOfLocationsMutable.value =
                        it//I don't know If i delete this line App no display characters WTF !!!!
                }


            }

        }
    }
}