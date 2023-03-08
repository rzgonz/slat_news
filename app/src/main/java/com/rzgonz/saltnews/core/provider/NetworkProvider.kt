package com.rzgonz.saltnews.core.provider

import com.google.gson.Gson
import com.rzgonz.saltnews.core.network.NetworkModule
import com.rzgonz.saltnews.core.network.RequestAuthInterceptor
import com.rzgonz.saltnews.core.utils.clazz
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

class NetworkProvider private constructor() : BaseModuleProvider {

    override val modules: List<Module>
        get() = listOf(networkModule)

    private val networkModule = module {
        single { Gson() }
        single { RequestAuthInterceptor(context = androidContext()) }
        single { NetworkModule.getClient() }
        single { NetworkModule.getRetrofit(okHttpClient = get()) }
    }


    companion object {

        @Volatile
        private var INSTANCE: NetworkProvider? = null

        @JvmStatic
        fun getInstance(): NetworkProvider {
            return INSTANCE ?: synchronized(clazz<NetworkProvider>()) {
                return@synchronized NetworkProvider()
            }.also {
                INSTANCE = it
            }
        }

    }
}