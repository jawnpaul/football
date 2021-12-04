package com.example.football.features.match.domain.model

sealed class TeamType()

data class TeamOne(
    val teamId: Int
) : TeamType()

data class TeamTwo(
    val teamId: Int
) : TeamType()