package com.example.football.features.match.domain.model

sealed class MatchAction

data class GoalAction(
    val teamId: Int
) : MatchAction()

data class YellowCardAction(
    val teamId: Int
) : MatchAction()

data class RedCardAction(
    val teamId: Int
) : MatchAction()

data class SubstitutionAction(
    val teamId: Int
) : MatchAction()