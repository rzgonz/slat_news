package com.rzgonz.saltnews.core.provider

import org.koin.core.module.Module

interface BaseModuleProvider {

    val modules: List<Module>

}