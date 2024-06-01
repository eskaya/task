package com.eskaya.task_sanstech.data.remote.models

import com.eskaya.task_sanstech.domain.model.Match

data class League(
    val leagueName: String,
    val flag: String,
    val matches: List<Match>
)