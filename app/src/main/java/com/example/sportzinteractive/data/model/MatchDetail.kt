package com.example.sportzinteractive.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MatchDetail(
    @SerializedName("Team_Home")
    val teamHome: String?=null,
    @SerializedName("Team_Away")
    val teamAway: String?=null,
    @SerializedName("Match")
    val match: MatchInfo,
    @SerializedName("Series")
    val series: Series,
    @SerializedName("Venue")
    val venue: Venue,
    @SerializedName("Officials")
    val officials: Officials,
    @SerializedName("Weather")
    val weather: String,
    @SerializedName("Tosswonby")
    val tossWonBy: String,
    @SerializedName("Status")
    val status: String,
    @SerializedName("Player_Match")
    val playerMatch: String,
    @SerializedName("Result")
    val result: String

) :Serializable