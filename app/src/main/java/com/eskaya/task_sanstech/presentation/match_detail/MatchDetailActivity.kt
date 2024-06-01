package com.eskaya.task_sanstech.presentation.match_detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eskaya.mvvm_application.R
import com.eskaya.mvvm_application.databinding.ActivityMatchDetailBinding
import com.eskaya.task_sanstech.domain.model.Match
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MatchDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchDetailBinding
    private var match: Match? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        match = intent.getParcelableExtra("match")
        init()
        listener()
    }

    private fun init() {

        if (match != null) {
            binding.tvHomeTeam.text = match?.homeTeam?.n
            binding.tvHomeTeamPointValue.text = match?.homeTeam?.p.toString()
            binding.tvHomeTeamScoreValue.text = match?.scorInfos?.ht?.r.toString()
            binding.tvHomeTeamGoalValue.text = match?.scorInfos?.ht?.c.toString()

            binding.tvAwayTeam.text = match?.awayTeam?.n
            binding.tvAwayTeamPointValue.text = match?.awayTeam?.p.toString()
            binding.tvAwayTeamScoreValue.text = match?.scorInfos?.at?.r.toString()
            binding.tvAwayTeamGoalValue.text = match?.scorInfos?.at?.c.toString()
        } else {
            Toast.makeText(
                this, getString(R.string.detailPage_errorMsg),
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    private fun listener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}