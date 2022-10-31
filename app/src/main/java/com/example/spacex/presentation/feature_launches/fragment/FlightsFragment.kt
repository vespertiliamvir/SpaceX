package com.example.spacex.presentation.feature_launches.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacex.databinding.FragmentFlightsBinding
import com.example.spacex.presentation.feature_launches.adapter.FlightAdapter
import com.example.spacex.presentation.feature_launches.viewmodel.LaunchesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightsFragment : Fragment() {

    private var _binding: FragmentFlightsBinding? = null
    private val binding: FragmentFlightsBinding get() = _binding!!
    private val viewModel by activityViewModels<LaunchesViewModel>()
    private val flightAdapter by lazy {
        FlightAdapter(this::onFlightClicked)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            flightListView.flightsRecyclerView.apply {
                adapter = flightAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }
            viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
                viewModel.viewState.collect { state ->
                    flightListView.flightsRecyclerView.isVisible = !state.isLoading
                    flightListView.progressCircular.isVisible = state.isLoading
                    if (state.flights.isNotEmpty()) {
                        flightAdapter.submitList(state.flights)
                    }
                    if (state.error != null) {
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
                        viewModel.onErrorShown()
                    }
                }
            }
        }
    }

    private fun onFlightClicked(flightNumber: Int) {
        viewModel.getFlightByFlightNumber(flightNumber)
        findNavController().navigate(FlightsFragmentDirections.actionFlightListFragmentToFlightDetailsFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}