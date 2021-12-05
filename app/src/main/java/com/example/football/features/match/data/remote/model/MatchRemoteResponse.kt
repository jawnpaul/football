package com.example.football.features.match.data.remote.model

import android.util.Log
import com.example.football.features.match.domain.model.MatchHeader
import com.example.football.features.match.domain.model.MatchResponse
import com.example.football.features.match.presentation.model.*
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import timber.log.Timber
import java.text.SimpleDateFormat

@JsonClass(generateAdapter = true)
data class MatchRemoteResponse(
    @field:Json(name = "resultCode") val resultCode: Int,
    @field:Json(name = "match") val match: Match
) {
    fun toDomainObject() =
        MatchResponse(
            header = MatchHeader(
                date = match.matchDate.toDate(),
                stadium = match.stadiumAddress,
                currentTime = match.matchTime.toString(),
                scores = match.teamOne.score.toString() + " : " + match.teamTwo.score.toString(),
                teamOne = match.teamOne.toPresentation(),
                teamTwo = match.teamTwo.toPresentation()
            ),
            actions = match.matchSummary.matchSummary.map { it.toString() }
            // actions = match.
            // matchSummary.matchSummary.
            // map { summary -> summary.actions.
            // map { action -> action.teamOneAction.actions.
            // map { generalAction -> generalAction.toDomainObject() } } }
            //actions = match.matchSummary.matchSummary[0].actions[0].teamOneAction.actions[0].toDomainObject()
        )
}
@JsonClass(generateAdapter = true)
data class Match(
    @field:Json(name = "matchDate") val matchDate: Long,
    @field:Json(name = "stadiumAdress") val stadiumAddress: String,
    @field:Json(name = "matchTime") val matchTime: Float,
    @field:Json(name = "team1") val teamOne: Team,
    @field:Json(name = "team2") val teamTwo: Team,
    @field:Json(name = "matchSummary") val matchSummary: MatchSummary
)

@JsonClass(generateAdapter = true)
data class Team(
    @field:Json(name = "teamName") val teamName: String,
    @field:Json(name = "teamImage") val teamImage: String,
    @field:Json(name = "score") val score: Int,
    @field:Json(name = "ballPosition") val ballPosition: Int
) {
    fun toPresentation() =
        TeamPresentation(
            teamName = teamName,
            teamLogo = teamImage,
            teamGoals = score.toString(),
            teamPossession = ballPosition.toString()
        )
}

@JsonClass(generateAdapter = true)
data class MatchSummary(
    @field:Json(name = "summaries") var matchSummary: List<Summary>
) {
    fun toDomain() = matchSummary.flatMap { it.toDomain() }
}

@JsonClass(generateAdapter = true)
data class Summary(
    val actions: List<Action>? = null
) {
    fun toDomain(): List<MatchAction> {
        doSomething()
        return emptyList()
    }

    fun doSomething(): Unit {
        Timber.d(actions?.size.toString())
    }
}


@JsonClass(generateAdapter = true)
data class Action(
    @field:Json(name = "actionTime") val actionTime: String,
    @field:Json(name = "team1Action") val teamOneAction: List<TeamOneAction>?,
    @field:Json(name = "team2Action") val teamTwoAction: List<TeamTwoAction>?
) {
    /*fun toDomain(): List<MatchAction> {
        val actions = arrayListOf<MatchAction>()
        val actionsOne = teamOneAction?.toDomainObjects()
        val actionsTwo = teamTwoAction?.toDomainObjects()
        if (actionsOne != null) {
            actions.addAll(actionsOne)
            Timber.d(actionsOne.size.toString())
        }
        if (actionsTwo != null) {
            actions.addAll(actionsTwo)
        }
        return actions
    }*/

    fun toDomain() = teamOneAction?.flatMap { it.toDomainObjects() }
}

@JsonClass(generateAdapter = true)
data class TeamOneAction(
    val actions: List<GeneralAction>
) {
    fun toDomainObjects(): List<MatchAction> {
        Timber.e("Got here")
        val actionss = actions.map { it.toDomainObject() }
        Timber.e(actionss.size.toString())
        return emptyList()
    }
}
@JsonClass(generateAdapter = true)
data class TeamTwoAction(
    val actions: List<GeneralAction>
) {
    fun toDomainObjects() = actions.map { it.toDomainObject() }
}

@JsonClass(generateAdapter = true)
data class Player(
    @field:Json(name = "playerName") val PlayerName: String,
    @field:Json(name = "playerImage") val PlayerImage: String
)

@JsonClass(generateAdapter = true)
data class SpecificAction(
    @field:Json(name = "player") val player: Player?,
    @field:Json(name = "goalType") val goalType: Int?,
    @field:Json(name = "player1") val playerOne: Player?,
    @field:Json(name = "player2") val playerTwo: Player?
)

@JsonClass(generateAdapter = true)
data class GeneralAction(
    @field:Json(name = "actionType") val actionType: Int,
    @field:Json(name = "teamType") val teamType: Int,
    @field:Json(name = "action") val action: SpecificAction
) {
    fun toDomainObject(): MatchAction {
        return when (actionType) {
            1 -> GoalAction(
                teamId = teamType,
                playerName = action.player?.PlayerName,
                playerPhoto = action.player?.PlayerImage,
                isOwnGoal = action.goalType == 2
            )

            2 -> {
                YellowCardAction(
                    teamId = teamType,
                    playerName = action.player?.PlayerName,
                    playerPhoto = action.player?.PlayerImage,
                )
            }

            3 -> {
                RedCardAction(
                    teamId = teamType,
                    playerName = action.player?.PlayerName,
                    playerPhoto = action.player?.PlayerImage,
                )
            }

            4 -> {
                SubstitutionAction(
                    teamId = teamType,
                    playerName = action.playerOne?.PlayerName,
                    playerPhoto = action.playerOne?.PlayerImage,
                    playerTwoName = action.playerTwo?.PlayerName,
                    playerTwoPhoto = action.playerTwo?.PlayerImage
                )
            }

            else -> {
                YellowCardAction(
                    teamId = teamType,
                    playerName = action.player?.PlayerName,
                    playerPhoto = action.player?.PlayerImage,
                )
            }
        }
    }
}

fun Long.toDate(): String {
    val dateFormat = "d MMMM yyyy"
    val date = SimpleDateFormat(dateFormat)
    return date.format(this)
}