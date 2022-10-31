package com.example.spacex.presentation.feature_launches.mapper

import com.example.spacex.data.network.model.SpaceXResponse
import com.example.spacex.presentation.feature_launches.model.FlightListItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun List<SpaceXResponse.Flight>.mapToFlightListItem(): List<FlightListItem> {
    return map { flight ->
        with(flight) {
            FlightListItem(
                flightNumber = flightNumber,
                missionName = flight.missionName,
                rocketName = rocket.rocketName,
                launchSiteName = launchSite.siteName,
                dateOfLaunch = formatDate(launchDateLocal),
                launchPatchImage = links.missionPath
            )
        }
    }
}

private fun formatDate(date: String): String {
    val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
    val parsedDate = LocalDateTime.parse(date, inputFormatter)
    val formatter = DateTimeFormatter.ofPattern("dd/M/yyyy hh:mm:ss a", Locale.US)
    return formatter.format(parsedDate)
}
