package com.rzgonz.saltnews.data.remote

/**
 * Created by rzgonz on 08/03/23.
 *
 */
class NewsRemoteDataSource(
    private val newsServices: NewsServices
) : NewsServices by newsServices