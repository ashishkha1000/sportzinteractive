package com.example.sportzinteractive.data.remote

import com.example.sportzinteractive.data.model.MatchData
import retrofit2.http.GET

interface APIService{

    //https://demo.sportz.io/sapk01222019186652.json
//    https://demo.sportz.io/nzin01312019187360.json
    @GET("sapk01222019186652.json")
    suspend fun getMatchDetails(): MatchData
}