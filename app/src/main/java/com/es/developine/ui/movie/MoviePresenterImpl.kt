package com.es.developine.ui.movie

import android.app.Application
import com.es.developine.ApplicationClass
import com.es.developine.network.INetworkApi
import com.es.developine.ui.Preseneter
import com.es.developine.ui.posts.PostPresenter
import com.es.developine.ui.posts.PostView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class MoviePresenterImpl(var postView: MovieView, var applicationComponent: Application) : MoviePresenter,Preseneter<MovieView>(postView) {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    init {
        (applicationComponent as ApplicationClass).movieComponent.inject(this)
    }

    override fun getAllMovies() {


        var view=view()

        view?.let {
            it.showLoading()
            var allPosts = mNetworkApi.getAllMovies()
            addDisposable(allPosts.subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                view?.let {
                                    it.hideLoading()
                                    postView.showAllMovies(result)

                                }
                            },
                            { error ->
                                view?.let {
                                    it.hideLoading()
                                }
                            }
                    ) )
        }

        if(view?.isShowing()!!){
            view.hideLoading()
        }

        var allPosts = mNetworkApi.getAllMovies()
        allPosts.subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    postView.showAllMovies(it)
                }

    }


}