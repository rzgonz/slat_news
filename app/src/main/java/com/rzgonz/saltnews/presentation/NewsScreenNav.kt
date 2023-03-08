package com.rzgonz.saltnews.presentation

import android.net.Uri
import com.google.gson.Gson
import com.rzgonz.saltnews.data.dto.ArticlesItemDto


sealed class NewsScreenNav(val route: String) {
    object Home : NewsScreenNav("home")
    object Detail : NewsScreenNav("news/{article}") {
        fun createRoute(articlesItemDto: ArticlesItemDto): String {
            val json = Uri.encode(Gson().toJson(articlesItemDto))
            return "news/$json"
        }
    }
}