package com.rzgonz.saltnews.data

import com.rzgonz.saltnews.core.utils.TimeUtils
import com.rzgonz.saltnews.data.dto.ArticlesItemDto
import com.rzgonz.saltnews.data.dto.NewsHeadlinesDto
import com.rzgonz.saltnews.data.dto.SourceDto
import com.rzgonz.saltnews.data.remote.response.ArticlesItemResponse
import com.rzgonz.saltnews.data.remote.response.NewsHeadlinesResponse
import com.rzgonz.saltnews.data.remote.response.SourceResponse

/**
 * Created by rzgonz on 12/02/23.
 *
 */
object DataMapper {

    fun sourceResponseToDto(response: SourceResponse?): SourceDto {
        return SourceDto(
            name = response?.name.orEmpty(),
            id = response?.id.toString()
        )
    }

    fun articlesItemResponseToDto(
        response: ArticlesItemResponse?
    ): ArticlesItemDto {
        return ArticlesItemDto(
            publishedAt = TimeUtils.formatStringDate(
                response?.publishedAt.orEmpty(),
                TimeUtils.DATE_FROM_BE,
                TimeUtils.DATE_IN_ID
            ),
            author = response?.author.orEmpty(),
            urlToImage = response?.urlToImage.orEmpty(),
            description = response?.description.orEmpty(),
            source = response?.source?.run { sourceResponseToDto(this) } ?: SourceDto(),
            title = response?.title.orEmpty(),
            url = response?.url.orEmpty(),
            content = response?.content.orEmpty()
        )
    }

    fun newsHeadlinesDto(
        response: NewsHeadlinesResponse?
    ): NewsHeadlinesDto {
        return NewsHeadlinesDto(
            status = response?.status.orEmpty(),
            articles = response?.articles?.map { articlesItemResponseToDto(it) }.orEmpty()
        )
    }

}