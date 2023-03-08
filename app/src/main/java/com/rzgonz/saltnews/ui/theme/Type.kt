package com.rzgonz.saltnews.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)


val Title = TextStyle(
    fontSize = 23.sp,
    fontWeight = FontWeight(700),
)

val SubTitle = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight(700),
)

val H1 = TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight(700),
)

val H2 = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight(700),
)

val H3 = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight(700),
)

val Body1 = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight(600),
)

val Caption1 = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight(700),
)

val Caption2 = TextStyle(
    fontSize = 10.sp,
    fontWeight = FontWeight(700),
)