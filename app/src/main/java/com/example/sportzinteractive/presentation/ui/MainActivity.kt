package com.example.sportzinteractive.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sportzinteractive.data.model.MatchData
import com.example.sportzinteractive.data.model.Team
import com.example.sportzinteractive.databinding.ActivityMainBinding
import com.example.sportzinteractive.presentation.viewmodel.MatchViewModel
import com.example.sportzinteractive.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var matchData: MatchData
    private lateinit var teamAway: Collection<Team>
    private lateinit var teamHome: Collection<Team>
    lateinit var binding: ActivityMainBinding
    val viewModel: MatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchData()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnExploreMore.setOnClickListener(this@MainActivity)
    }

    private fun fetchData() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.matchDetails.collect { matchDetails ->
                    when (matchDetails) {
                        is ApiState.Loading -> {
                            // Show loading UI
                        }
                        is ApiState.Success -> {
                            matchData = matchDetails.data
                            // Use matchDetails.data
                            setData(matchData)
                        }
                        is ApiState.Error -> {
                            // Show error message
                        }
                    }
                }
            }
        }
    }

    private fun setData(match: MatchData) {
        binding.apply {
            teamHome = match.teams.filterKeys { it.startsWith(match.matchDetail.teamHome.toString()) }.values
            teamAway = match.teams.filterKeys { it.startsWith(match.matchDetail.teamAway.toString()) }.values
            tvTeamA.text = teamHome.first().nameFull
            tvTeamB.text = teamAway.first().nameFull

            txtStadium.text = match.matchDetail.venue.name
            tvMatchDate.text = "${match.matchDetail.match.date} ${match.matchDetail.match.time}"

        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.btnExploreMore.id->{
                navigateToPlayerInformation()
            }
        }
    }

    private fun navigateToPlayerInformation() {
        val bundle = Bundle()
        bundle.putString("team_a",binding.tvTeamA.text.toString())
        bundle.putString("team_b",binding.tvTeamB.text.toString())
        bundle.putSerializable("match_data",matchData)
        val intent = Intent(this, PlayerInformationActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
