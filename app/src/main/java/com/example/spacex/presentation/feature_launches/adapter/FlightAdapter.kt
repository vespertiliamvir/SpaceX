package com.example.spacex.presentation.feature_launches.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.databinding.ListItemFlightBinding
import com.example.spacex.presentation.feature_launches.model.FlightListItem
import com.example.spacex.presentation.utils.loadImage

class FlightAdapter(
    private val onFlightClick: (Int) -> Unit
) : ListAdapter<FlightListItem, FlightAdapter.FlightViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        return FlightViewHolder(
            binding = ListItemFlightBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onFlightClick = onFlightClick
        )
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class FlightViewHolder(
        private val binding: ListItemFlightBinding,
        private val onFlightClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(flight: FlightListItem) = with(binding) {
            patchImage.loadImage(flight.launchPatchImage.orEmpty())
            missionNameText.text = flight.missionName
            rocketNameText.text = flight.rocketName
            launchSiteNameText.text = flight.launchSiteName
            dateOfLaunchText.text = flight.dateOfLaunch

            root.setOnClickListener {
                onFlightClick(flight.flightNumber)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<FlightListItem>() {
        override fun areItemsTheSame(oldItem: FlightListItem, newItem: FlightListItem): Boolean {
            return oldItem.flightNumber == newItem.flightNumber
        }

        override fun areContentsTheSame(oldItem: FlightListItem, newItem: FlightListItem): Boolean {
            return oldItem == newItem
        }
    }
}