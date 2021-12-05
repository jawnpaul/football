package com.example.football.features.match.presentation.model

data class GoalPresentation(
    val teamId: Int,
    val playerName: String,
    val playerPhoto: String,
    val isOwnGoal: Boolean = false
): Goal()