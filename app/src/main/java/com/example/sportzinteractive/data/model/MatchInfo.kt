package com.example.sportzinteractive.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MatchInfo(
    @SerializedName("Id")
    val id: String?=null,
    @SerializedName("Number")
    val number: String?=null,
    @SerializedName("Type")
    val type: String?=null,
    @SerializedName("Date")
    val date: String?=null,
    @SerializedName("Time")
    val time: String?=null,
    @SerializedName("Daynight")
    val daynight: String
): Serializable
