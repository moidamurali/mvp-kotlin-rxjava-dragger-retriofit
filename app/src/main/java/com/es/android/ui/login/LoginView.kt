package com.es.android.ui.login

import com.es.android.ui.IView

interface LoginView: IView {

    fun navigateToHome()

    fun onBackPress()

    fun onPasswordError()
}