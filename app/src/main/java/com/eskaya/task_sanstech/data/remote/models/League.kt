package com.eskaya.task_sanstech.data.remote.models

data class League(
    val leagueName: String,
    val flag: String,
    val matches: List<com.eskaya.task_sanstech.data.remote.models.response.Data>
)