package com.example.football.core.api

import com.example.football.features.match.data.remote.model.MatchRemoteResponse
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("v2/5b9264193300006b00205fb9")
    suspend fun getMatch(): Response<MatchRemoteResponse>
}