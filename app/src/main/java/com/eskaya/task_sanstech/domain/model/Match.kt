package com.eskaya.task_sanstech.domain.model

import android.os.Parcelable
import com.eskaya.task_sanstech.data.remote.models.response.At
import com.eskaya.task_sanstech.data.remote.models.response.Br
import com.eskaya.task_sanstech.data.remote.models.response.Ht
import com.eskaya.task_sanstech.data.remote.models.response.Sc
import com.eskaya.task_sanstech.data.remote.models.response.To
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    val awayTeam: At?,
    val br: Br?,
    val matchDate: Long?,
    val homeTeam: Ht?,
    val id: Int?,
    val scorInfos: Sc?,
    val to: To?,
) : Parcelable