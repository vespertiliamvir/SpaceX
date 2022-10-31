package com.example.spacex.presentation.feature_launches.model

data class FlightListItem(
    val flightNumber: Int,
    val missionName: String,
    val rocketName: String,
    val launchSiteName: String,
    val dateOfLaunch: String,
    val launchPatchImage: String?
)
