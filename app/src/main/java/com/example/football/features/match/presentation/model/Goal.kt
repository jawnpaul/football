package com.example.football.features.match.presentation.model

sealed class Goal

data class NormalGoal(
    val teamId: Int,
    val playerName: String,
    val playerPhoto: String
) : Goal() {
    fun toPresentation() =
        GoalPresentation(
            teamId = teamId,
            isOwnGoal = false,
            playerName = playerName,
            playerPhoto = playerName
        )
}

data class OwnGoal(
    val teamId: Int,
    val playerName: String,
    val playerPhoto: String
) : Goal() {
    fun toPresentation() =
        GoalPresentation(
            teamId = teamId,
            isOwnGoal = true,
            playerName = playerName,
            playerPhoto = playerPhoto
        )
}