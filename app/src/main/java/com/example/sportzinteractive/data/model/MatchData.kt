package com.example.sportzinteractive.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MatchData(
    @SerializedName("Matchdetail")
    val matchDetail: MatchDetail,
    @SerializedName("Nuggets")
    val nuggets: List<String>,
    @SerializedName("Innings")
    val innings: List<Inning>,
    @SerializedName("Teams")
    val teams: Map<String, Team>
) : Serializable
