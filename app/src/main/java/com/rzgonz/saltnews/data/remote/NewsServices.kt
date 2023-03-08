package com.rzgonz.saltnews.data.remote

import com.rzgonz.saltnews.data.remote.response.NewsHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rzgonz on 08/03/23.
 *
 */
interface NewsServices {
    @GET("top-headlines")
    suspend fun getHeadline(
        @Query("q") query: String
    ): NewsHeadlinesResponse
}