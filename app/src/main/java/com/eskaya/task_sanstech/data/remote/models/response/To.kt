package com.eskaya.task_sanstech.data.remote.models.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class To(
    val flag: String,
    val i: Int,
    val n: String,
    val p: Int,
    val sn: String
):Parcelable