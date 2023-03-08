package com.rzgonz.saltnews.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rzgonz.saltnews.core.Async
import com.rzgonz.saltnews.core.Fail
import com.rzgonz.saltnews.core.Loading
import com.rzgonz.saltnews.core.Success
import com.rzgonz.saltnews.core.Uninitialized
import com.rzgonz.saltnews.core.utils.logD
import com.rzgonz.saltnews.data.dto.NewsHeadlinesDto
import com.rzgonz.saltnews.domain.NewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by rzgonz on 08/03/23.
 *
 */
data class HomeState(
    val listHeadlineAsync: Async<NewsHeadlinesDto> = Uninitialized
)


class HomeViewModel(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    val querySearch = mutableStateOf(TextFieldValue(""))

    init {
        getHeadline()
    }

    fun getHeadline(query: String = "") {
        _state.update { it.copy(listHeadlineAsync = Loading()) }
        viewModelScope.launch {
            try {
                val data = newsUseCase.getHeadline(query)
                logD<HomeViewModel>("room : ${data.articles.size}")
                _state.update { it.copy(listHeadlineAsync = Success(data)) }
            } catch (e: Exception) {
                _state.update { it.copy(listHeadlineAsync = (Fail(e))) }
            }
        }
    }
}