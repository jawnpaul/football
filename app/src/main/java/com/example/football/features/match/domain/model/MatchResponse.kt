package com.example.football.features.match.domain.model

import com.example.football.features.match.data.remote.model.MatchRemoteResponse
import com.example.football.features.match.presentation.model.MatchAction
import com.example.football.features.match.presentation.model.TeamPresentation

data class MatchResponse(
    val header: MatchHeader,
    val res: MatchRemoteResponse
)

data class MatchHeader(
    val date: String,
    val stadium: String,
    val currentTime: String,
    val scores: String,
    val teamOne: TeamPresentation,
    val teamTwo: TeamPresentation
)
