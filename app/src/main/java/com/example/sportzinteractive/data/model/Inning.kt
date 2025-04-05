package com.example.sportzinteractive.data.model

import java.io.Serializable

data class Inning(
    val number: String,
    val battingTeam: String,
    val total: String,
    val wickets: String,
    val overs: String,
    val runrate: String,
    val batsmen: List<Batsman>,
    val bowlers: List<Bowler>
):Serializable