package com.example.football.core.di

import com.example.football.features.match.data.repository.MatchRepository
import com.example.football.features.match.domain.repository.IMatchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {
    @Binds
    abstract fun bindsMatchRepository(repo: MatchRepository): IMatchRepository
}
