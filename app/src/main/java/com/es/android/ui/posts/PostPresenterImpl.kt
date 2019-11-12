package com.es.android.ui.posts

import android.app.Application
import com.es.android.ApplicationClass
import com.es.android.network.INetworkApi
import com.es.android.ui.Preseneter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class PostPresenterImpl(var postView: PostView, var applicationComponent: Application) : PostPresenter, Preseneter<PostView>(postView) {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    init {
        (applicationComponent as ApplicationClass).applicationComponent.inject(this)
    }

    override fun getAllPosts() {


        var view=view()

        view?.let {
            it.showLoading()
            var allPosts = mNetworkApi.getAllPosts()
            addDisposable(allPosts.subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                view?.let {
                                    it.hideLoading()
                                    postView.showAllPosts(result)

                                }
                            },
                            { error ->
                                view?.let {
                                    it.hideLoading()
                                }
                            }
                    ) ) }
        if(view?.isShowing()!!){
            view.hideLoading()
        }

        var allPosts = mNetworkApi.getAllPosts()
        allPosts.subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    postView.showAllPosts(it)
                }

    }


}