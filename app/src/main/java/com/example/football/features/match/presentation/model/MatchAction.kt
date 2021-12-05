package com.example.football.features.match.presentation.model

sealed class MatchAction

data class GoalAction(
    val teamId: Int,
    val playerName: String?,
    val playerPhoto: String?,
    val isOwnGoal: Boolean
) : MatchAction()

data class YellowCardAction(
    val teamId: Int,
    val playerName: String?,
    val playerPhoto: String?,
) : MatchAction()

data class RedCardAction(
    val teamId: Int,
    val playerName: String?,
    val playerPhoto: String?,
) : MatchAction()

data class SubstitutionAction(
    val teamId: Int,
    val playerName: String?,
    val playerPhoto: String?,
    val playerTwoName: String?,
    val playerTwoPhoto: String?,
) : MatchAction()