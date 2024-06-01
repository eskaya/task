package com.eskaya.task_sanstech.presentation.match_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eskaya.mvvm_application.databinding.ActivityMatchDetailBinding

class MatchDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMatchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {}
}