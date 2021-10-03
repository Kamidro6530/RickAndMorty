package com.example.rickandmortycompose.hilt

import androidx.annotation.NonNull
import com.example.rickandmortycompose.retrofit.CharacterService
import com.example.rickandmortycompose.retrofit.EpisodeService
import com.example.rickandmortycompose.retrofit.LocationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://rickandmortyapi.com/")
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterService(@NonNull retrofit: Retrofit) : CharacterService{
        return retrofit.create(CharacterService::class.java)
    }

    @Provides
    @Singleton
    fun provideEpisodeService(@NonNull retrofit: Retrofit) : EpisodeService{
        return retrofit.create(EpisodeService::class.java)
    }
    @Provides
    @Singleton
    fun provideLocationService(@NonNull retrofit: Retrofit) : LocationService{
        return retrofit.create(LocationService::class.java)
    }




}
