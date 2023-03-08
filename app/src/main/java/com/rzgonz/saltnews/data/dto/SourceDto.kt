package com.rzgonz.saltnews.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceDto(
    val name: String = "",
    val id: String = ""
) : Parcelable