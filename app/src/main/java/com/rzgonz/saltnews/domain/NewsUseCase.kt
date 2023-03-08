package com.rzgonz.saltnews.domain

import com.rzgonz.saltnews.data.dto.NewsHeadlinesDto

/**
 * Created by rzgonz on 08/03/23.
 *
 */
interface NewsUseCase {

    suspend fun getHeadline(query: String): NewsHeadlinesDto
}