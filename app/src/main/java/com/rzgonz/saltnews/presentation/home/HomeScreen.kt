package com.rzgonz.saltnews.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.rzgonz.saltnews.R
import com.rzgonz.saltnews.core.Fail
import com.rzgonz.saltnews.core.Loading
import com.rzgonz.saltnews.core.Success
import com.rzgonz.saltnews.core.Uninitialized
import com.rzgonz.saltnews.data.dto.ArticlesItemDto
import com.rzgonz.saltnews.data.dto.NewsHeadlinesDto
import com.rzgonz.saltnews.presentation.NewsScreenNav
import com.rzgonz.saltnews.ui.theme.Caption1
import com.rzgonz.saltnews.ui.theme.DefaultBackground
import com.rzgonz.saltnews.ui.theme.H1
import com.rzgonz.saltnews.ui.theme.H2
import com.rzgonz.saltnews.ui.theme.Primary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.koinViewModel

/**
 * Created by rzgonz on 08/03/23.
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Salt News",
                        style = H1,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Primary,
                    titleContentColor = Color.White
                )
            )
        }, content = {
            HomeScreenContainer(
                it,
                homeViewModel.state.collectAsState(),
                onItemClick = { articleData ->
                    navController.navigate(NewsScreenNav.Detail.createRoute(articleData))
                })
        }
    )
}

@Composable
fun HomeScreenContainer(
    paddingValues: PaddingValues, state: State<HomeState>,
    onItemClick: (ArticlesItemDto) -> Unit
) {
    ListNewsView(paddingValues, state, onItemClick = onItemClick)

}

private fun LazyListScope.error(
    message: String
) {
    item {
        Text(
            text = message,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.error
        )
    }
}

private fun LazyListScope.loading() {
    item {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListNewsView(
    paddingValues: PaddingValues,
    state: State<HomeState>,
    onItemClick: (ArticlesItemDto) -> Unit
) {
    val listArticles = state.value.listHeadlineAsync.invoke()?.articles.orEmpty()
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .background(DefaultBackground()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        stickyHeader {
            SearchView()
        }

        items(listArticles) {
            CardNewsView(it, onItemClick = onItemClick)
        }

        when (val stateList = state.value.listHeadlineAsync) {
            is Fail -> {
                error(stateList.error)
            }
            is Loading -> loading()
            is Success -> {

            }
            Uninitialized -> loading()
        }
    }
}


@Composable
fun SearchView(
    homeViewModel: HomeViewModel = koinViewModel()
) {
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(12.dp)
    ) {
        TextField(
            value = homeViewModel.querySearch.value,
            onValueChange = { value ->
                homeViewModel.querySearch.value = value
                homeViewModel.getHeadline(value.text)
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            },
            trailingIcon = {
                if (homeViewModel.querySearch.value != TextFieldValue("")) {
                    IconButton(
                        onClick = {
                            homeViewModel.querySearch.value =
                                TextFieldValue("")
                            homeViewModel.getHeadline()
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            placeholder = {
                androidx.compose.material.Text("Search")
            },
            singleLine = true,
            shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                cursorColor = Color.Black,
                leadingIconColor = Color.Black,
                trailingIconColor = Color.Black,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardNewsView(
    articlesItemDto: ArticlesItemDto,
    onItemClick: (ArticlesItemDto) -> Unit,
    isPreview: Boolean = false
) {
    Row(
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.Gray
                ), shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke(articlesItemDto)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isPreview) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(120.dp)
                    .height(90.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )
        } else {
            GlideImage(
                model = articlesItemDto.urlToImage, contentDescription = "",
                modifier = Modifier
                    .width(120.dp)
                    .height(90.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = articlesItemDto.title, style = H2
            )
            Text(
                text = "Publish : ${articlesItemDto.publishedAt}",
                color = Color.Gray, style = Caption1
            )
            if (articlesItemDto.author.isEmpty().not()) {
                Text(
                    text = "Author : ${articlesItemDto.author}", style = Caption1
                )
            }
        }
    }
}

@Preview(name = "Preview")
@Composable
private fun PreviewCardNews() {
    CardNewsView(
        articlesItemDto = ArticlesItemDto(
            title = "TITLtetete",
            author = "AUTOHOHTOHTOTH"
        ),
        isPreview = true,
        onItemClick = {}
    )
}

@Preview(name = "Preview")
@Composable
private fun PreviewHome() {
    val state = MutableStateFlow(HomeState())
    state.update {
        it.copy(
            listHeadlineAsync = Success(
                NewsHeadlinesDto()
            )
        )
    }
    HomeScreenContainer(
        paddingValues = PaddingValues(),
        state = state.collectAsState(),
        onItemClick = {

        }
    )
}