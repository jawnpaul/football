package com.example.football.features.match.domain.usecases

import com.example.football.core.baseusecase.BaseUseCase
import com.example.football.core.exception.Failure
import com.example.football.core.functional.Either
import com.example.football.features.match.domain.model.MatchResponse
import com.example.football.features.match.domain.repository.IMatchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMatchUseCase @Inject constructor(private val repository: IMatchRepository) :
    BaseUseCase<GetMatchUseCase.None, MatchResponse>() {
    class None

    override suspend fun run(params: None): Flow<Either<Failure, MatchResponse>> {
        return repository.getMatch()
    }
}