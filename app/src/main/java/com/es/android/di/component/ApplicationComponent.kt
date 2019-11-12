package com.es.android.di.component

import com.es.android.ApplicationClass
import com.es.android.di.module.AppModule
import com.es.android.di.module.NetModule
import com.es.android.ui.login.LoginPresenterImpl
import com.es.android.ui.movie.MoviePresenterImpl
import com.es.android.ui.posts.PostPresenterImpl
import dagger.Component

@Component(modules = [AppModule::class, NetModule::class])
interface ApplicationComponent {

    fun inject(mewApplication: ApplicationClass)
    fun inject(mLoginPresenterImpl: LoginPresenterImpl)
    fun inject(mPostPresenterImpl: PostPresenterImpl)
    fun inject(mMoviePresenterImpl: MoviePresenterImpl)

}