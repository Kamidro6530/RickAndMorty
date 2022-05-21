package com.example.rickandmortycompose.viewmodel


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.repositories.LocationRepository
import com.example.rickandmortycompose.retrofit.locations.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val locationRepository: LocationRepository) :
    ViewModel() {

    private val _listOfLocations = MutableStateFlow<MutableList<Location>>(mutableStateListOf<Location>())//List for Location List
    var listOfLocations = _listOfLocations

    init {
        viewModelScope.launch {


                 locationRepository.getLocations().collect {
                     if (it != null)
                    listOfLocations.value.addAll(it)

            }

        }
    }
}