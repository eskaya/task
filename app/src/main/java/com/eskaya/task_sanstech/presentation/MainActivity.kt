package com.eskaya.task_sanstech.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eskaya.mvvm_application.R
import com.eskaya.mvvm_application.databinding.ActivityMainBinding

import com.eskaya.task_sanstech.presentation.match_list.MatchListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        val fragment = MatchListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
}