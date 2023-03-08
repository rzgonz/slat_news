package com.rzgonz.saltnews.core.provider

import com.rzgonz.saltnews.core.utils.clazz
import com.rzgonz.saltnews.data.NewsRepository
import com.rzgonz.saltnews.data.remote.NewsRemoteDataSource
import com.rzgonz.saltnews.data.remote.NewsServices
import com.rzgonz.saltnews.domain.NewsUseCase
import com.rzgonz.saltnews.domain.NewsUseCaseImpl
import com.rzgonz.saltnews.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.binds
import org.koin.dsl.module
import retrofit2.Retrofit

class AppModulesProvider {

    val appModules: List<Module>
        get() {
            return ArrayList<Module>().apply {
                addAll(networkModules)
                addAll(listOf(appModule))
                addAll(listOf(interactorModule))
                addAll(listOf(viewModelModule))
            }
        }


    private val appModule = module {
        single {
            provideWebService(retrofit = get())
        }

        single {
            NewsRemoteDataSource(newsServices = get())
        }


        single {
            NewsRepository(newsRemoteDataSource = get())
        }

    }

    private val interactorModule = module {
        factory {
            NewsUseCaseImpl(
                newsRepository = get()
            )
        } binds arrayOf(NewsUseCase::class)

    }

    private val viewModelModule = module {
        viewModel { HomeViewModel(newsUseCase = get()) }
    }


    private val networkModules by lazy {
        NetworkProvider.getInstance().modules
    }


    private fun provideWebService(retrofit: Retrofit) = retrofit.create(clazz<NewsServices>())

    companion object {

        @Volatile
        private var INSTANCE: AppModulesProvider? = null

        @JvmStatic
        fun getInstance(): AppModulesProvider {
            return INSTANCE ?: synchronized(clazz<AppModulesProvider>()) {
                return@synchronized AppModulesProvider()
            }.also {
                INSTANCE = it
            }
        }

    }
}