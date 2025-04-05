package com.example.sportzinteractive.data.repository

import com.example.sportzinteractive.data.model.MatchData
import com.example.sportzinteractive.utils.ApiState
import com.example.sportzinteractive.data.remote.APIService
import com.example.sportzinteractive.domain.repository.MatchRepository

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val apiService: APIService
) : MatchRepository {
    override suspend fun fetchMatchDetails(): ApiState<MatchData> {
        return try {
            ApiState.Success(apiService.getMatchDetails())
        } catch (e: IOException) {
            ApiState.Error("Network Error: Unable to fetch data")
        } catch (e: HttpException) {
            ApiState.Error("Server Error: ${e.message()}")
        }
    }
}