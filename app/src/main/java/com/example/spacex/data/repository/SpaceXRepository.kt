package com.example.spacex.data.repository

import com.example.spacex.data.network.model.SpaceXResponse
import com.example.spacex.data.network.retrofit.SpaceXService
import com.example.spacex.data.utils.NetworkState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SpaceXRepository @Inject constructor(
    private val spaceXService: SpaceXService
) : ISpaceXRepository {

    private var launches = emptyList<SpaceXResponse.Flight>()

    override suspend fun getLaunches() = flow {
        emit(NetworkState.Loading)
        val response = runCatching {
            val response = spaceXService.getLaunches()
            if (response.isSuccessful && response.body() != null) {
                val flights = response.body()!!
                launches = flights
                NetworkState.Success(flights)
            } else {
                NetworkState.Failure("empty response")
            }
        }.getOrElse {
            NetworkState.Failure(it.localizedMessage ?: "Unexpected Error")
        }
        emit(response)
    }

    override fun getFlightByNumber(flightNumber: Int): SpaceXResponse.Flight? {
        return launches.find { it.flightNumber == flightNumber }
    }
}