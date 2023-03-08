package com.rzgonz.saltnews.core.network

import android.content.Context
import android.os.Build
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class RequestAuthInterceptor(
    private val context: Context
) : Interceptor, KoinComponent {
    var locale: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        context.resources.configuration.locales[0].country
    } else {
        context.resources.configuration.locale.country
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apiKey", NetworkUtils.authToken)
            .addQueryParameter("country", locale)
            .build()

        // Request customization: add request headers

        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }


}