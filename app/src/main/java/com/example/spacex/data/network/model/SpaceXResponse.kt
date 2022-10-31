package com.example.spacex.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpaceXResponse(
    val flights: List<Flight>
) {

    @JsonClass(generateAdapter = true)
    data class Flight(
        @get:Json(name = "flight_number")
        val flightNumber: Int,
        @get:Json(name = "mission_name")
        val missionName: String,
        @get:Json(name = "mission_id")
        val missionId: List<String>,
        val upcoming: Boolean,
        @get:Json(name = "launch_year")
        val launchYear: String,
        @get:Json(name = "launch_date_unix")
        val launchDateUnix: Long,
        @get:Json(name = "launch_date_utc")
        val launchDateUtc: String,
        @get:Json(name = "launch_date_local")
        val launchDateLocal: String,
        @get:Json(name = "is_tentative")
        val isTentative: Boolean,
        @get:Json(name = "tentative_max_precision")
        val tentativeMaxPrecision: String,
        val tbd: Boolean,
        @get:Json(name = "launch_window")
        val launchWindow: Int?,
        val rocket: Rocket,
        val ships: List<String>,
        val telemetry: Telemetry,
        @get:Json(name = "launch_site")
        val launchSite: LaunchSite,
        val launchSuccess: Boolean? = null,
        @get:Json(name = "launch_failure_details")
        val launchFailureDetails: LaunchFailureDetails? = null,
        val links: Links,
        val details: String?,
        @get:Json(name = "static_fire_date_utc")
        val staticFireDateUtc: String?,
        @get:Json(name = "static_fire_date_unix")
        val staticFireDateUnix: Long?,
        val timeline: Timeline?,
        val crew: Any?,
        @get:Json(name = "last_date_update")
        val lastDateUpdate: String? = null,
        @get:Json(name = "last_ll_launch_date")
        val lastLlLaunchDate: String?,
        @get:Json(name = "last_ll_update")
        val lastLlUpdate: String?,
        @get:Json(name = "last_wiki_launch_date")
        val lastWikiLaunchDate: String?,
        @get:Json(name = "last_wiki_revision")
        val lastWikiRevision: String?,
        @get:Json(name = "last_wiki_update")
        val lastWikiUpdate: String?,
        @get:Json(name = "launch_date_source")
        val launchDateSource: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Rocket(
        @get:Json(name = "rocket_id")
        val rocketId: String,
        @get:Json(name = "rocket_name")
        val rocketName: String,
        @get:Json(name = "rocket_type")
        val rocketType: String,
        @get:Json(name = "first_stage")
        val firstStage: FirstStage,
        @get:Json(name = "second_stage")
        val secondStage: SecondStage,
        val fairings: Fairings?
    ) {

        @JsonClass(generateAdapter = true)
        data class FirstStage(
            val cores: List<Core>
        ) {

            @JsonClass(generateAdapter = true)
            data class Core(
                @get:Json(name = "core_serial")
                val coreSerial: String?,
                val flight: Int?,
                val block: Int?,
                val gridfins: Boolean?,
                val legs: Boolean?,
                val reused: Boolean?,
                @get:Json(name = "land_success")
                val landSuccess: Boolean?,
                @get:Json(name = "landing_intent")
                val landingIntent: Boolean?,
                @get:Json(name = "landing_type")
                val landingType: String?,
                @get:Json(name = "landing_vehicle")
                val landingVehicle: String?
            )
        }

        @JsonClass(generateAdapter = true)
        data class SecondStage(
            val block: Int?,
            val payloads: List<Payload>
        ) {

            @JsonClass(generateAdapter = true)
            data class Payload(
                @get:Json(name = "payload_id")
                val payloadId: String,
                @get:Json(name = "norad_id")
                val noradId: List<Int>,
                val reused: Boolean,
                val customers: List<String>,
                val nationality: String? = null,
                val manufacturer: String?,
                @get:Json(name = "payload_type")
                val payloadType: String,
                @get:Json(name = "payload_mass_kg")
                val payloadMassKg: Double?,
                @get:Json(name = "payload_mass_lbs")
                val payloadMassLbs: Double?,
                val orbit: String,
                @get:Json(name = "orbit_params")
                val orbitParams: OrbitParams,
                @get:Json(name = "mass_returned_kg")
                val massReturnedKg: Double? = null,
                @get:Json(name = "mass_returned_lbs")
                val massReturnedLbs: Double? = null,
                @get:Json(name = "flight_time_sec")
                val flightTimeSec: Long? = null,
                @get:Json(name = "cargo_manifest")
                val cargoManifest: String? = null
            ) {

                @JsonClass(generateAdapter = true)
                data class OrbitParams(
                    @get:Json(name = "reference_system")
                    val referenceSystem: String?,
                    val regime: String?,
                    val longitude: Double?,
                    @get:Json(name = "semi_major_axis_km")
                    val semiMajorAxisKm: Double?,
                    val eccentricity: Double?,
                    @get:Json(name = "periapsis_km")
                    val periapsisKm: Double?,
                    @get:Json(name = "apoapsis_km")
                    val apoapsisKm: Double?,
                    @get:Json(name = "inclination_deg")
                    val inclinationDeg: Double?,
                    @get:Json(name = "period_min")
                    val periodMin: Double?,
                    @get:Json(name = "lifespan_years")
                    val lifespanYears: Double?,
                    val epoch: String?,
                    @get:Json(name = "mean_motion")
                    val meanMotion: Double?,
                    val raan: Double?,
                    @get:Json(name = "arg_of_pericenter")
                    val argOfPericenter: Double?,
                    @get:Json(name = "mean_anomaly")
                    val meanAnomaly: Double?
                )
            }
        }

        @JsonClass(generateAdapter = true)
        data class Fairings(
            val reused: Boolean?,
            @get:Json(name = "recovery_attempt")
            val recoveryAttempt: Boolean?,
            val recovered: Boolean?,
            val ship: String?
        )
    }

    @JsonClass(generateAdapter = true)
    data class Telemetry(
        @get:Json(name = "flight_club")
        val flightClub: String?
    )

    @JsonClass(generateAdapter = true)
    data class LaunchSite(
        @get:Json(name = "site_id")
        val siteId: String,
        @get:Json(name = "site_name")
        val siteName: String,
        @get:Json(name = "site_name_long")
        val siteNameLong: String
    )

    @JsonClass(generateAdapter = true)
    data class LaunchFailureDetails(
        val time: Long,
        val altitude: Int?,
        val reason: String
    )

    @JsonClass(generateAdapter = true)
    data class Timeline(
        @get:Json(name = "webcast_liftoff")
        val webcastLiftOff: Int? = null
    )

    @JsonClass(generateAdapter = true)
    data class Links(
        @get:Json(name = "mission_patch")
        val missionPath: String?,
        @get:Json(name = "mission_patch_small")
        val missionPathSmall: String?,
        @get:Json(name = "reddit_campaign")
        val redditCampaign: String?,
        @get:Json(name = "reddit_launch")
        val redditLaunch: String?,
        @get:Json(name = "reddit_recovery")
        val redditRecovery: String?,
        @get:Json(name = "reddit_media")
        val redditMedia: String?,
        @get:Json(name = "article_link")
        val articleLink: String?,
        @get:Json(name = "video_link")
        val videoLink: String?,
        @get:Json(name = "youtube_id")
        val youtubeId: String?,
        @get:Json(name = "flickr_images")
        val flickrImages: List<String>
    )
}
