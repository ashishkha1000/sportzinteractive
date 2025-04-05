package com.example.sportzinteractive.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Bowling(
    @SerializedName("Style")
    val style: String,
    @SerializedName("Average")
    val average: String,
    @SerializedName("Economyrate")
    val economyrate: String,
    @SerializedName("Wickets")
    val wickets: String
): Serializable