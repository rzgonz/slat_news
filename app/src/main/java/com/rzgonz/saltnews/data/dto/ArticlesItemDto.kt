package com.rzgonz.saltnews.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticlesItemDto(

    val publishedAt: String = "",

    val author: String = "",

    val urlToImage: String = "",

    val description: String = "",

    val source: SourceDto = SourceDto(),

    val title: String = "",

    val url: String = "",

    val content: String = ""
): Parcelable