package com.example.rickandmortycompose.repositories

import com.example.rickandmortycompose.retrofit.LocationService
import com.example.rickandmortycompose.retrofit.locations.Location
import com.example.rickandmortycompose.retrofit.locations.LocationList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocationRepository @Inject constructor(private val locationService: LocationService) {

    fun getLocations(number: Int): Observable<List<Location>>? {

        var data: Observable<List<Location>>? =
            locationService.getLocations("/api/location/?page=${number}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(LocationList::results)
                .flatMapIterable {listOf(it) }


        return data

    }
}