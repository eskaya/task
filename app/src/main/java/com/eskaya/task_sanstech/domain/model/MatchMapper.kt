package com.eskaya.task_sanstech.domain.model

import android.annotation.SuppressLint
import com.eskaya.task_sanstech.data.remote.models.response.Data


@SuppressLint("NotConstructor")
class MatchMapper {
    fun fromDtoToDomain(movieItem: Data): Match = Match(
        awayTeam = movieItem.at,
        br = movieItem.br,
        matchDate = movieItem.d,
        homeTeam = movieItem.ht,
        id = movieItem.i,
        scorInfos = movieItem.sc,
        to = movieItem.to,
    )
}