package com.es.developine

import android.app.Application
import com.es.developine.di.component.ApplicationComponent
import com.es.developine.di.component.DaggerApplicationComponent
import com.es.developine.di.module.NetModule

open class ApplicationClass : Application() {


    public lateinit var applicationComponent: ApplicationComponent
    public lateinit var movieComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .netModule(NetModule("https://jsonplaceholder.typicode.com/"))
                .build()

        applicationComponent.inject(this)

        //Newly added From this onwards for Json ListView
        movieComponent = DaggerApplicationComponent.builder()
                .netModule(NetModule("https://api.androidhive.info/json/"))
                .build()

        movieComponent.inject(this)
    }
}