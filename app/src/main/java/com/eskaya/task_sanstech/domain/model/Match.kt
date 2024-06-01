package com.eskaya.task_sanstech.domain.model

import com.eskaya.task_sanstech.data.remote.models.response.At
import com.eskaya.task_sanstech.data.remote.models.response.Br
import com.eskaya.task_sanstech.data.remote.models.response.Ht
import com.eskaya.task_sanstech.data.remote.models.response.Pe
import com.eskaya.task_sanstech.data.remote.models.response.Sc
import com.eskaya.task_sanstech.data.remote.models.response.To
import com.eskaya.task_sanstech.data.remote.models.response.İmg

data class Match(

    val awayTeam: At?, //at
    val br: Br?, //br
    val bri: Int?,
    val matchDate: Long?, //d
    val homeTeam: Ht?, //ht
    val id: Int?, //i
    val img: İmg?,
    val pe: Pe?,
    val scorInfos: Sc?, //sc
    val sgi: Int?,
    val st: String?,
    val str: Boolean?,
    val to: To?, //to
    val v: String?
)