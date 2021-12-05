package com.example.football.features.match.domain.model

import com.example.football.features.match.presentation.model.GoalPresentation
import com.example.football.features.match.presentation.model.NormalGoal
import com.example.football.features.match.presentation.model.OwnGoal

data class GoalDomain(
    val teamId: Int,
    val playerName: String,
    val playerPhoto: String,
    val isOwnGoal: Boolean
) {
    fun toPresentation(): GoalPresentation {
        return when (isOwnGoal) {
            true -> {
                NormalGoal(teamId, playerName, playerPhoto).toPresentation()
            }
            false -> {
                OwnGoal(teamId, playerName, playerPhoto).toPresentation()
            }
        }
    }
}