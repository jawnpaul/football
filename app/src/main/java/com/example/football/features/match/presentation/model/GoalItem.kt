package com.example.football.features.match.presentation.model

import android.view.View
import coil.load
import com.example.football.R
import com.example.football.databinding.SingleGoalItemBinding
import com.xwray.groupie.databinding.BindableItem

class GoalItem(private val item: GoalPresentation) : BindableItem<SingleGoalItemBinding>() {
    override fun bind(binding: SingleGoalItemBinding, p1: Int) {
        when (item.teamId) {
            1 -> {
                //if is ownGoal, make ball red
                binding.teamOneGoal.visibility = View.VISIBLE
                binding.teamTwoGoal.visibility = View.GONE
                binding.playerName.text = item.playerName
                binding.playerImage.load(item.playerPhoto)
            }
            2 -> {
                // if is ownGoal, make ball red
                binding.teamOneGoal.visibility = View.GONE
                binding.teamTwoGoal.visibility = View.VISIBLE
                binding.playerTwoName.text = item.playerName
                binding.playerTwoImage.load(item.playerPhoto)
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.single_goal_item
    }

}