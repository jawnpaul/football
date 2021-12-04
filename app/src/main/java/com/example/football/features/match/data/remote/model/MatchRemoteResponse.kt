package com.example.football.features.match.data.remote.model

import com.example.football.features.match.domain.model.MatchResponse
import com.squareup.moshi.Json

data class MatchRemoteResponse(
    @field:Json(name = "resultCode") val resultCode: Int,
    @field:Json(name = "match") val match: Match
) {
    fun toDomainObject() = MatchResponse(name = resultCode.toString())
}

data class Match(
    @field:Json(name = "matchDate") val matchDate: Long,
    @field:Json(name = "stadiumAddress") val stadiumAddress: String,
    @field:Json(name = "matchTime") val matchTime: Float,
    @field:Json(name = "team1") val teamOne: Team,
    @field:Json(name = "team2") val teamTwo: Team,
    @field:Json(name = "matchSummary") val matchSummary: MatchSummary
)

data class Team(
    @field:Json(name = "teamName") val teamName: String,
    @field:Json(name = "teamImage") val teamImage: String,
    @field:Json(name = "score") val score: Int,
    @field:Json(name = "ballPosition") val ballPosition: Int
)

data class MatchSummary(
    @field:Json(name = "summaries") val matchSummary: List<Summary>
)

data class Summary(
    val actions: List<Action>
)

data class Action(
    @field:Json(name = "actionTime") val actionTime: String,
    @field:Json(name = "team1Action") val teamOneAction: TeamOneAction?,
    @field:Json(name = "team2Action") val teamTwoAction: TeamTwoAction?
)

data class TeamOneAction(
    val actions: List<GeneralAction>
)

data class TeamTwoAction(
    val actions: List<GeneralAction>
)

data class Player(
    @field:Json(name = "playerName") val PlayerName: String,
    @field:Json(name = "playerImage") val PlayerImage: String
)

data class SpecificAction(
    @field:Json(name = "player") val player: Player
)

data class GeneralAction(
    @field:Json(name = "actionType") val actionType: Int,
    @field:Json(name = "teamType") val teamType: Int,
    @field:Json(name = "action") val action: SpecificAction
)