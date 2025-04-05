package com.example.sportzinteractive.presentation.ui


import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sportzinteractive.data.model.MatchData
import com.example.sportzinteractive.databinding.ActivityPlayerInformationBinding
import com.example.sportzinteractive.presentation.viewmodel.MatchViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerInformationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var teamA: String
    private lateinit var teamB: String
    private lateinit var matchData: MatchData
    lateinit var binding: ActivityPlayerInformationBinding
    val viewModel: MatchViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromBundle()
        setTab()
    }

    private fun getDataFromBundle() {
        teamA = intent.extras?.getString("team_a")?:""
        teamB = intent.extras?.getString("team_b")?:""
        matchData = intent.extras?.getSerializable("match_data") as MatchData
    }

    private fun setTab() {

        val adapter = PlayerViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        // Connect TabLayout and ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "ALL"
                1 -> teamA
                else -> teamB
            }
        }.attach()

        //default all players
        val bundle = Bundle().apply {
            putString("key_data", "ALL")
            putSerializable("match_data",matchData)
        }
        supportFragmentManager.setFragmentResult("data_request_${0}",bundle)
        //all players

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val tag = when (tab.position) {
                    0 -> {
                        //get the tab/team name and return the all tab player list
                        val bundle = Bundle().apply {
                            putString("key_data", "ALL")
                            putSerializable("match_data",matchData)
                        }
                        supportFragmentManager.setFragmentResult("data_request_${tab.position}",bundle)
                    }
                    1 -> {
                        //get the tab/team name and return the team A tab player list
                        val bundle = Bundle().apply {
                            putString("key_data", teamA)
                            putSerializable("match_data",matchData)
                        }
                        supportFragmentManager.setFragmentResult("data_request_${tab.position}",bundle)
                    }
                    else -> {
                        //get the tab/team name and return the team B tab player list
                        val bundle = Bundle().apply {
                            putString("key_data", teamB)
                            putSerializable("match_data",matchData)
                        }
                        supportFragmentManager.setFragmentResult("data_request_${tab.position}",bundle)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        //for tab space
        binding.tabLayout.post {
            val tabStrip = binding.tabLayout.getChildAt(0) as ViewGroup
            for (i in 0 until tabStrip.childCount) {
                val tab = tabStrip.getChildAt(i)
                val layoutParams = tab.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.setMargins(16, 0, 16, 0)
                tab.layoutParams = layoutParams
            }
        }

    }

    override fun onClick(p0: View?) {
    }


}


