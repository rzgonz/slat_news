package com.rzgonz.saltnews.data.remote.response

import com.google.gson.annotations.SerializedName

data class ArticlesItemResponse(

    @field:SerializedName("publishedAt")
	val publishedAt: String? = null,

    @field:SerializedName("author")
	val author: String? = null,

    @field:SerializedName("urlToImage")
	val urlToImage: String? = null,

    @field:SerializedName("description")
	val description: String? = null,

    @field:SerializedName("source")
	val source: SourceResponse? = null,

    @field:SerializedName("title")
	val title: String? = null,

    @field:SerializedName("url")
	val url: String? = null,

    @field:SerializedName("content")
	val content: String? = null
)