package com.example.football.features.match.data.repository

import com.example.football.core.api.ApiService
import com.example.football.core.exception.Failure
import com.example.football.core.functional.Either
import com.example.football.features.match.domain.model.MatchResponse
import com.example.football.features.match.domain.repository.IMatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MatchRepository @Inject constructor(private val apiService: ApiService) : IMatchRepository {
    override suspend fun getMatch(): Flow<Either<Failure, MatchResponse>> = flow {
        try {
            val res = apiService.getMatch()
            emit(
                when (res.isSuccessful) {
                    true -> {
                        res.body()?.let { it ->
                            Either.Right(it.toDomainObject())
                        } ?: Either.Left(Failure.DataError)
                    }
                    false -> {
                        Either.Left(Failure.ServerError)
                    }
                }
            )
        } catch (e: Exception) {
            emit(Either.Left(Failure.ServerError))
        }
    }
}