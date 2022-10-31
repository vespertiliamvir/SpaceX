package com.example.spacex.data.repository

import com.example.spacex.data.network.model.SpaceXResponse
import com.example.spacex.data.utils.NetworkState
import kotlinx.coroutines.flow.Flow

interface ISpaceXRepository {
    suspend fun getLaunches(): Flow<NetworkState<List<SpaceXResponse.Flight>>>

    fun getFlightByNumber(flightNumber: Int): SpaceXResponse.Flight?
}