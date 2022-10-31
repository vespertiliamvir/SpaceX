package com.example.spacex.presentation.feature_launches.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import com.example.spacex.databinding.FragmentFlightDetailsBinding
import com.example.spacex.presentation.feature_launches.viewmodel.LaunchesViewModel

class FlightDetailsFragment : Fragment() {

    private var _binding: FragmentFlightDetailsBinding? = null
    private val binding: FragmentFlightDetailsBinding get() = _binding!!
    private val viewModel by activityViewModels<LaunchesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.viewState.collect { state ->
                binding.flightDetailsText.text = state.selectedFlight.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}