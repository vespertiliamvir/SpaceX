package com.example.spacex.presentation.feature_launches.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacex.data.network.model.SpaceXResponse
import com.example.spacex.data.repository.SpaceXRepository
import com.example.spacex.data.utils.NetworkState
import com.example.spacex.presentation.feature_launches.mapper.mapToFlightListItem
import com.example.spacex.presentation.feature_launches.model.FlightListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) : ViewModel() {

    private var _viewState = MutableStateFlow(ViewState())
    val viewState = _viewState.asStateFlow()

    init {
        getLaunches()
    }

    fun getFlightByFlightNumber(flightNumber: Int) {
        val flight = spaceXRepository.getFlightByNumber(flightNumber)
        _viewState.value = _viewState.value.copy(selectedFlight = flight)
    }

    private fun getLaunches() {
        viewModelScope.launch {
            spaceXRepository.getLaunches().collect { launches ->
                when (launches) {
                    NetworkState.Loading -> {
                        _viewState.update { state -> state.copy(isLoading = true) }
                    }
                    is NetworkState.Success -> {
                        _viewState.value = ViewState(flights = launches.data.mapToFlightListItem())
                    }
                    is NetworkState.Failure -> {
                        _viewState.update { state ->
                            state.copy(
                                isLoading = false, error = launches.message
                            )
                        }
                    }
                }
            }
        }
    }

    fun onErrorShown() {
        _viewState.update { it.copy(error = null) }
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val flights: List<FlightListItem> = emptyList(),
        val selectedFlight: SpaceXResponse.Flight? = null,
        val error: String? = null
    )
}