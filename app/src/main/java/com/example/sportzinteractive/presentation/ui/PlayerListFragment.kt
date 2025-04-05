package com.example.sportzinteractive.presentation.ui

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sportzinteractive.R
import com.example.sportzinteractive.data.model.MatchData
import com.example.sportzinteractive.data.model.Player
import com.example.sportzinteractive.databinding.FragmentPlayerListBinding
import com.example.sportzinteractive.presentation.viewmodel.MatchViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlayerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class PlayerListFragment : Fragment() {

    private lateinit var binding: FragmentPlayerListBinding
    private val viewModel: MatchViewModel by viewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()

    }


    private fun setData() {
        val tabPosition = arguments?.getInt("tab_position") ?: 0

        parentFragmentManager.setFragmentResultListener("data_request_$tabPosition", this) { _, bundle ->
            val match = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable("match_data", MatchData::class.java)
            } else {
                @Suppress("DEPRECATION")
                bundle.getSerializable("match_data") as? MatchData
            }

            //onclick of tab
            val teamFullNames = ArrayList<String>()
            val teamIds = ArrayList<String>()

            for ((teamId, team) in match!!.teams) {
                teamFullNames.add(team.nameFull)
                teamIds.add(teamId)
            }

            var teamName = "ALL"

            val data = bundle.getString("key_data")
            teamName = data.toString()

            val teams = match.teams

            val allPlayers = teams.values.flatMap { it.players.values }
            val filteredPlayers = when (teamName) {
                teamFullNames[0] -> teams[teamIds[0]]?.players?.values ?: emptyList()
                teamFullNames[1] -> teams[teamIds[1]]?.players?.values ?: emptyList()
                else -> allPlayers
            }

            binding.recyclerView.layoutManager = GridLayoutManager(requireActivity(),2)
            val gridAdapter = GridAdapter(){player->
                showAlertDialog(player)
            }
            binding.recyclerView.adapter = gridAdapter
            gridAdapter.submitList(filteredPlayers.toList())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(tabPosition: Int?=null, match: MatchData?=null) =
            PlayerListFragment().apply {
                arguments = Bundle().apply {
                    putInt("tab_position", tabPosition?:0)
                }
            }
    }

    private fun showAlertDialog(player:Player){
        val dialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_layout, null)

        val alertDialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(true) // or false if you want to block back press
            .create()

        val battingStyle = dialogView.findViewById<TextView>(R.id.tv_tag)
        battingStyle.text = "Batting Style: ${player.batting.style}"

        val bowlingStyle = dialogView.findViewById<TextView>(R.id.bowlingStyle)
        bowlingStyle.text = "Bowling Style: ${player.bowling.style}"

        val playerName = dialogView.findViewById<TextView>(R.id.playerName)
        playerName.text = player.nameFull

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()

    }
}