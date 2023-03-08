package com.rzgonz.saltnews.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.rzgonz.saltnews.R
import com.rzgonz.saltnews.component.HtmlText
import com.rzgonz.saltnews.component.HyperlinkText
import com.rzgonz.saltnews.component.ignoreParentPadding
import com.rzgonz.saltnews.data.dto.ArticlesItemDto
import com.rzgonz.saltnews.data.dto.SourceDto
import com.rzgonz.saltnews.ui.theme.Body1
import com.rzgonz.saltnews.ui.theme.H1
import com.rzgonz.saltnews.ui.theme.Primary

/**
 * Created by rzgonz on 08/03/23.
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(
    navHostController: NavHostController,
    articlesItemDto: ArticlesItemDto
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail News",
                        style = H1,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }, navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                )
            )
        }, content = {
            NewsDetailContainer(it, data = articlesItemDto, isPreview = false)
        }
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetailContainer(
    it: PaddingValues,
    isPreview: Boolean = false,
    data: ArticlesItemDto
) {
    Column(
        modifier = Modifier
            .padding(it)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (isPreview) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .ignoreParentPadding(12.dp)
                    .fillMaxWidth()
            )
        } else {
            GlideImage(
                model = data.urlToImage, contentDescription = "",
                modifier = Modifier
                    .ignoreParentPadding(12.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = data.title,
            color = Color.Black,
            style = H1
        )

        Text(
            text = "Publish Date : ${data.publishedAt}",
            color = Color.Gray,
            style = Body1
        )

        HtmlText(
            html = data.content,
        )

        Row {
            HyperlinkText(
                fullText = "Authors : ${data.author}",
                hyperLinks = mutableMapOf(
                    data.author to data.url,
                ),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    color = Gray
                ),
                linkTextColor = Primary,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }

}

@Preview(name = "Preview")
@Composable
private fun PreviewHome() {
    NewsDetailScreen(
        navHostController = rememberNavController(), articlesItemDto = ArticlesItemDto(
            source = SourceDto(name = "Reuteres", id = "returers"),
            author = "authors",
            title = "French nationwide strike to extend as pension reform fight intensifies - Reuters",
            description = "France's nationwide strike against a planned pension reform, which disrupted train services, shut schools and halted <a href=\"/business/energy/totalenergies-fuel-deliveries-halted-its-french-sites-due-pension-reform-strike-2023-03-07/\">fuel deliveries</a>, wi…",
            url = "https://www.reuters.com/world/europe/unions-seek-bring-france-halt-block-pension-reform-2023-03-07/",
            publishedAt = "2023-03-08T05:00:00Z",
            urlToImage = "https://www.reuters.com/resizer/Of7NFtJxiekrCZ89qwQE3hx_Kew=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/5EBDCCAO3NKJLBZLNMI76M3IYU.jpg",
            content = "PARIS, March 7 (Reuters) - France's nationwide strike against a planned pension reform, which disrupted train services, shut schools and halted fuel deliveries, will spill into Wednesday as unions se… [+4006 chars]"
        )
    )
}
