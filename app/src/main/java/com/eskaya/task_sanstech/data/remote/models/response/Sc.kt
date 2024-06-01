package com.eskaya.task_sanstech.data.remote.models.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Sc(
    val abbr: String,
    val at: AtX,
    val ht: HtX,
    val min: Int,
    val st: Int
):Parcelable