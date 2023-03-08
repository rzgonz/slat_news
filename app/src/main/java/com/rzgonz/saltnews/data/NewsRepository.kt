package com.rzgonz.saltnews.data

import com.rzgonz.saltnews.data.remote.NewsRemoteDataSource
import com.rzgonz.saltnews.data.remote.NewsServices

/**
 * Created by rzgonz on 08/03/23.
 *
 */
class NewsRepository(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsServices by newsRemoteDataSource {

}