package com.example.sportzinteractive.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Venue(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String
): Serializable