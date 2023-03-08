package com.rzgonz.saltnews

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.rzgonz.saltnews.presentation.NewsNavigation
import com.rzgonz.saltnews.presentation.NewsScreenNav
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by rzgonz on 08/03/23.
 *
 */
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NewsNavigation(navController = navController)
        }
    }

    // Unit test
    @Test
    fun appNavHost_verifyStartDestination() {
        composeTestRule
            .onNodeWithContentDescription("Start Screen")
            .assertIsDisplayed()
    }

    @Test
    fun appNavHost_clickNewsItem_navigateToDetail() {
        composeTestRule.onNodeWithContentDescription("All Profiles")
            .performScrollTo()
            .performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, NewsScreenNav.Detail.route)
    }
}