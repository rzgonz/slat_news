package com.rzgonz.saltnews.data.remote.response

import com.google.gson.annotations.SerializedName

data class SourceResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Any? = null
)