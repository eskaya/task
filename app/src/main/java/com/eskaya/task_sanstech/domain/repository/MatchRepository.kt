package com.eskaya.task_sanstech.domain.repository

import com.eskaya.task_sanstech.data.remote.models.response.*

interface MatchRepository {

    suspend fun getMatchList(): MatchListDto

}