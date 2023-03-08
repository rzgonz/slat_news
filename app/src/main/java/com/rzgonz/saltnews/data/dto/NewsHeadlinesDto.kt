package com.rzgonz.saltnews.data.dto

import com.google.gson.annotations.SerializedName

data class NewsHeadlinesDto(

    @field:SerializedName("articles")
    val articles: List<ArticlesItemDto> = listOf(),

    @field:SerializedName("status")
    val status: String? = null
)