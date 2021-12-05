package com.example.football.features.match.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import com.example.football.R
import com.example.football.databinding.ActivityMainBinding
import com.example.football.features.match.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getMatch()

        mainViewModel.matchHeader.observe(this, {
            binding.date.text = it.date
            binding.stadium.text = it.stadium
            binding.currentTime.text = it.currentTime
            binding.scoresTextView.text = it.scores
            binding.teamOneImageView.load(it.teamOne.teamLogo)
            binding.teamTwoImageView.load(it.teamTwo.teamLogo)
            binding.teamOneNameTextView.text = it.teamOne.teamName
            binding.teamTwoNameTextView.text = it.teamTwo.teamName
            binding.teamOnePossession.text = it.teamOne.teamPossession
            binding.teamTwoPossession.text = it.teamTwo.teamPossession
        })
    }
}