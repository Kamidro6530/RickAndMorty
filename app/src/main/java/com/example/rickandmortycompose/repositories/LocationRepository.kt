package com.example.rickandmortycompose.repositories

import com.example.rickandmortycompose.retrofit.LocationService
import com.example.rickandmortycompose.retrofit.locations.Location
import com.example.rickandmortycompose.retrofit.locations.LocationList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocationRepository @Inject constructor(private val locationService: LocationService) {

    suspend fun getLocations(): Flow<List<Location>?> {

        return flow {
            for (number in 1..6) {
                val data = locationService.getLocations("/api/location/?page=${number}")
                emit(data.body()?.results)
            }
        }.flowOn(Dispatchers.IO)


    }
}