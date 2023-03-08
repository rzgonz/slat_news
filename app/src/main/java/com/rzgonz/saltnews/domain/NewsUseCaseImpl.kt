package com.rzgonz.saltnews.domain

import com.rzgonz.saltnews.data.DataMapper
import com.rzgonz.saltnews.data.NewsRepository
import com.rzgonz.saltnews.data.dto.NewsHeadlinesDto

/**
 * Created by rzgonz on 08/03/23.
 *
 */
class NewsUseCaseImpl(
    private val newsRepository: NewsRepository
) : NewsUseCase {
    override suspend fun getHeadline(query: String): NewsHeadlinesDto {
        val response = newsRepository.getHeadline(query)
        return DataMapper.newsHeadlinesDto(response)
    }

}