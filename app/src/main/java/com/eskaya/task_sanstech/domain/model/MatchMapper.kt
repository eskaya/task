package com.eskaya.task_sanstech.domain.model

import android.annotation.SuppressLint
import com.eskaya.task_sanstech.data.remote.models.response.Data


@SuppressLint("NotConstructor")
class MatchMapper {
    fun fromDtoToDomain(movieItem: Data): Match = Match(
        awayTeam = movieItem.at,
        br = movieItem.br,
        bri = movieItem.bri,
        matchDate = movieItem.d,
        homeTeam = movieItem.ht,
        id = movieItem.i,
        img = movieItem.img,
        pe = movieItem.pe,
        scorInfos = movieItem.sc,
        sgi = movieItem.sgi,
        st = movieItem.st,
        str = movieItem.str,
        to = movieItem.to,
        v = movieItem.v
    )
}