package com.example.football.features.match.domain.model

sealed class Goal

data class NormalGoal(
    val teamId: Int
) : Goal()

data class OwnGoal(
    val teamId: Int
) : Goal()