package com.eskaya.task_sanstech.domain.repository

import com.eskaya.task_sanstech.domain.model.Match

interface MatchRepository {

    suspend fun getMatchList():List<Match>

}