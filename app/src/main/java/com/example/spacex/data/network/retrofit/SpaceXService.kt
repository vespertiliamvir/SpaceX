package com.example.spacex.data.network.retrofit

import com.example.spacex.data.network.model.SpaceXResponse
import retrofit2.Response
import retrofit2.http.GET

interface SpaceXService {

    @GET("v3/launches")
    suspend fun getLaunches(): Response<List<SpaceXResponse.Flight>>
}