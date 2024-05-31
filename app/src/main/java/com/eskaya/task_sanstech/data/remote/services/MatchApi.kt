package com.eskaya.task_sanstech.data.remote.services

import com.eskaya.task_sanstech.data.remote.models.response.*
import retrofit2.http.GET

interface MatchApi {
    @GET("statistics/sport/SOCCER/matches")
    suspend fun getMatchList(): MatchListDto
}