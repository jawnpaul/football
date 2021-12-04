package com.example.football.features.match.domain.repository

import com.example.football.core.exception.Failure
import com.example.football.core.functional.Either
import com.example.football.features.match.data.remote.model.MatchRemoteResponse
import com.example.football.features.match.domain.model.MatchResponse
import kotlinx.coroutines.flow.Flow

interface IMatchRepository {

    suspend fun getMatch(): Flow<Either<Failure, MatchResponse>>
}