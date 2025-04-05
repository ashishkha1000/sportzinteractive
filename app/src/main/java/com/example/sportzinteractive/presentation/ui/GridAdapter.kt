package com.example.sportzinteractive.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sportzinteractive.R
import com.example.sportzinteractive.data.model.Player
import com.example.sportzinteractive.databinding.ItemGridBinding

class GridAdapter(private val callBack: (Player) -> Unit) :
    ListAdapter<Player, GridAdapter.GridViewHolder>(DiffCallback()) {


    inner class GridViewHolder(private val binding: ItemGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Player) {
            binding.apply {
                if (item.isKeeper == true && item.isCaptain == true) {
                    txtView.text = "${item.nameFull} (WK) (C)"
                } else if (item.isCaptain == true) {
                    txtView.text = "${item.nameFull} (C)"
                } else if (item.isKeeper == true) {
                    txtView.text = "${item.nameFull} (WK)"
                } else {
                    txtView.text = "${item.nameFull}"
                }

                itemRoot.setOnClickListener {
                    callBack.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val binding = ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(getItem(position))


    }

    class DiffCallback : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem == newItem // Or use ID if you have a model class
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem == newItem // Change this for complex models
        }
    }
}
