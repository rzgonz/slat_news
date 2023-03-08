package com.rzgonz.saltnews.presentation.detail

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.rzgonz.saltnews.data.dto.ArticlesItemDto

/**
 * Created by rzgonz on 08/03/23.
 *
 */
class NewsDetailArgs : NavType<ArticlesItemDto>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ArticlesItemDto? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): ArticlesItemDto {
        return Gson().fromJson(value, ArticlesItemDto::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ArticlesItemDto) {
        bundle.putParcelable(key, value)
    }
}