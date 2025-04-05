package com.example.sportzinteractive.domain.usecase

import com.example.sportzinteractive.data.model.MatchData
import com.example.sportzinteractive.utils.ApiState
import com.example.sportzinteractive.domain.repository.MatchRepository
import javax.inject.Inject

class GetMatchDetailsUseCase @Inject constructor(
    private val repository: MatchRepository
) {
    suspend operator fun invoke(): ApiState<MatchData> {
        return repository.fetchMatchDetails()
    }
}