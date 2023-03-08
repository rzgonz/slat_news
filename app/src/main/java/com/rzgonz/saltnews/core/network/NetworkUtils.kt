package com.rzgonz.saltnews.core.network

import android.content.Context

object NetworkUtils {

    private var baseUrl = "https://newsapi.org/v2/"

    val authToken: String = "f8c00f75d2d0470e9db62428942defda"

    fun getBaseUrl(): String = baseUrl
}
