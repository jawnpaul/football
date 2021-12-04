package com.example.football.features.match.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.football.core.exception.Failure
import com.example.football.core.functional.onFailure
import com.example.football.core.functional.onSuccess
import com.example.football.features.match.domain.model.MatchResponse
import com.example.football.features.match.domain.usecases.GetMatchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getMatchUseCase: GetMatchUseCase) :
    ViewModel() {
    private val job = Job()

    fun getMatch() {
        getMatchUseCase(job, GetMatchUseCase.None()) {
            it.fold(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleFailure(failure: Failure) {
        Timber.e(failure.toString())
    }

    private fun handleSuccess(response: MatchResponse) {
        Timber.d(response.toString())
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}