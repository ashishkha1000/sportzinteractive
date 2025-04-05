package com.example.sportzinteractive.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportzinteractive.data.model.MatchData
import com.example.sportzinteractive.utils.ApiState
import com.example.sportzinteractive.domain.usecase.GetMatchDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val getMatchDetailsUseCase: GetMatchDetailsUseCase
) : ViewModel() {
    private val _matchDetails = MutableStateFlow<ApiState<MatchData>>(ApiState.Loading)
    val matchDetails: StateFlow<ApiState<MatchData>> get() = _matchDetails

    init {
        fetchMatchDetails()
    }

    private fun fetchMatchDetails() {
        viewModelScope.launch {
            _matchDetails.value = getMatchDetailsUseCase()
        }
    }
}
