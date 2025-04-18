package com.example.sportzinteractive.presentation.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PlayerViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3



    override fun createFragment(position: Int): Fragment {
        return when (position) {
            else -> PlayerListFragment.newInstance(position)
        }
    }
}
