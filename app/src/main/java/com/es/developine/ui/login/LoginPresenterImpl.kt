package com.es.developine.ui.login

import android.app.Application
import com.es.developine.ApplicationClass
import com.es.developine.network.INetworkApi
import com.es.developine.ui.Preseneter
import javax.inject.Inject

class LoginPresenterImpl(var loginViewInit: LoginView, var applicationComponent: Application) : LoginPresenter,Preseneter<LoginView>(loginViewInit) {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    init {
        (applicationComponent as ApplicationClass).applicationComponent.inject(this)
    }

    override fun validateUser(userName: String, userPassword: String) {

    }

    override fun peformLogin(userName: String, userPassword: String) {
        if (userName == "hammad") {
            loginViewInit.navigateToHome()
        }
    }
}