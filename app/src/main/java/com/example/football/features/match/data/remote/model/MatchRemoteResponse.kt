package com.example.football.features.match.data.remote.model

import com.example.football.features.match.domain.model.MatchHeader
import com.example.football.features.match.domain.model.MatchResponse
import com.example.football.features.match.presentation.model.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import timber.log.Timber
import java.text.SimpleDateFormat

data class MatchRemoteResponse(
    @SerializedName("resultCode")
    @Expose
    val resultCode: Int,
    @SerializedName("match")
    @Expose val match: Match
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
            res = this
            // actions = match.
            // matchSummary.matchSummary.
            // map { summary -> summary.actions.
            // map { action -> action.teamOneAction.actions.
            // map { generalAction -> generalAction.toDomainObject() } } }
            //actions = match.matchSummary.matchSummary[0].actions[0].teamOneAction.actions[0].toDomainObject()
        )
}

data class Match(
    @SerializedName("matchDate")
    @Expose
    val matchDate: Long,
    @SerializedName("stadiumAdress")
    @Expose
    val stadiumAddress: String,
    @SerializedName("matchTime")
    @Expose
    val matchTime: Float,
    @SerializedName("team1")
    @Expose
    val teamOne: Team,
    @SerializedName("team2")
    @Expose
    val teamTwo: Team,
    @SerializedName("matchSummary")
    @Expose
    val matchSummary: MatchSummary
)

data class Team(
    val teamName: String,
    val teamImage: String,
    val score: Int,
    val ballPosition: Int
) {
    fun toPresentation() =
        TeamPresentation(
            teamName = teamName,
            teamLogo = teamImage,
            teamGoals = score.toString(),
            teamPossession = ballPosition.toString()
        )
}

data class MatchSummary(
    @SerializedName("summaries")
    @Expose
    val matchSummary: List<Summary?>?
) {
    // fun toDomain() = matchSummary.flatMap { it.toDomain() }
}


data class Summary(
    val actions: List<Action>?
) {
    fun toDomain(): List<MatchAction> {
        doSomething()
        return emptyList()
    }

    fun doSomething(): Unit {
        Timber.e(actions?.size.toString())
    }
}


data class Action(
    @SerializedName("actionTime")
    @Expose
    val actionTime: String?,
    @SerializedName("team1Action")
    @Expose
    val teamOneAction: List<TeamOneAction?>?,
    @SerializedName("team2Action")
    @Expose
    val teamTwoAction: List<TeamTwoAction?>?
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

    // fun toDomain() = teamOneAction?.flatMap { it.toDomainObjects() }
}

data class TeamOneAction(
    val actions: List<GeneralAction?>?
) {
    /*fun toDomainObjects(): List<MatchAction> {
        Timber.e("Got here")
        val actionss = actions.map { it.toDomainObject() }
        Timber.e(actionss.size.toString())
        return emptyList()
    }*/
}

data class TeamTwoAction(
    val actions: List<GeneralAction?>?
) {
    // fun toDomainObjects() = actions.map { it.toDomainObject() }
}


data class Player(
    @SerializedName("playerName")
    @Expose
    val PlayerName: String?,
    @SerializedName("playerImage")
    @Expose
    val PlayerImage: String?
)


data class SpecificAction(
    @SerializedName("player")
    @Expose
    val player: Player?,
    @SerializedName("goalType")
    @Expose
    val goalType: Int?,
    @SerializedName("player1")
    @Expose
    val playerOne: Player?,
    @SerializedName("player2")
    @Expose
    val playerTwo: Player?
)

data class GeneralAction(
    @SerializedName("actionType")
    @Expose
    val actionType: Int?,
    @SerializedName("teamType")
    @Expose
    val teamType: Int?,
    @SerializedName("action")
    @Expose
    val action: SpecificAction?
) {
    /*fun toDomainObject(): MatchAction {
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
    }*/
}

fun Long.toDate(): String {
    val dateFormat = "d MMMM yyyy"
    val date = SimpleDateFormat(dateFormat)
    return date.format(this)
}