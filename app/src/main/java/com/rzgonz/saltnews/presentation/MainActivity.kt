package com.rzgonz.saltnews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rzgonz.saltnews.data.dto.ArticlesItemDto
import com.rzgonz.saltnews.presentation.detail.NewsDetailArgs
import com.rzgonz.saltnews.presentation.detail.NewsDetailScreen
import com.rzgonz.saltnews.presentation.home.HomeScreen
import com.rzgonz.saltnews.ui.theme.SaltNewsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaltNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsNavigation(navController = rememberNavController())
                }
            }
        }
    }
}

@Composable
fun NewsNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = "news_graph",
        startDestination = NewsScreenNav.Home.route
    ) {
        composable(route = NewsScreenNav.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = NewsScreenNav.Detail.route,
            arguments = listOf(navArgument("article") {
                type = NewsDetailArgs()
            })
        ) { backStackEntry ->
            val articlesItemDto =
                backStackEntry.arguments?.getParcelable<ArticlesItemDto>("article")
            requireNotNull(articlesItemDto) { "komandoId parameter wasn't found. Please make sure it's set!" }
            NewsDetailScreen(navHostController = navController, articlesItemDto = articlesItemDto)
        }
    }
}
