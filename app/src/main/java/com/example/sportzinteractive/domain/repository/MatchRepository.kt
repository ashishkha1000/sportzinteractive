package com.example.sportzinteractive.domain.repository

import com.example.sportzinteractive.data.model.MatchData
import com.example.sportzinteractive.utils.ApiState
import dagger.hilt.android.scopes.ActivityScoped

interface MatchRepository {
    suspend fun fetchMatchDetails(): ApiState<MatchData>
}
