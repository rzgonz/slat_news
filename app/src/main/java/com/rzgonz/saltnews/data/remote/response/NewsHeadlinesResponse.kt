package com.rzgonz.saltnews.data.remote.response

import com.google.gson.annotations.SerializedName

data class NewsHeadlinesResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItemResponse?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)