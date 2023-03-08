package com.rzgonz.saltnews

import android.app.Application
import android.content.Intent
import com.rzgonz.saltnews.core.provider.AppModulesProvider
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module


open class Applications : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    fun setupKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Applications)

            modules(mutableListOf<Module>().apply {
                addAll(
                    AppModulesProvider.getInstance().appModules
                )
            })
        }
    }

}