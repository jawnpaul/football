package com.example.football.features.match.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.football.core.exception.Failure
import com.example.football.core.functional.onFailure
import com.example.football.core.functional.onSuccess
import com.example.football.features.match.domain.model.MatchHeader
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

    private val _matchHeader = MutableLiveData<MatchHeader>()
    val matchHeader: LiveData<MatchHeader> get() = _matchHeader

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
        Timber.e(response.res.toString())
        _matchHeader.value = response.header
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}