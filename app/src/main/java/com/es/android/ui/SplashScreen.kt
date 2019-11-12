package com.es.android.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.es.android.R
import com.es.android.ui.posts.PostActivity

class SplashScreen: BaseActivity(){
    override fun setLayout(): Int {
        loasSplashView()
        return R.layout.splash_screen
    }

    override fun init(savedInstanceState: Bundle?) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartScreen() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopScreen() {
        //To change body of created functions use File | Settings | File Templates.
    }

    private val SPLASH_TIME_OUT:Long=3000 // 3 sec

    
    private fun loasSplashView() {

        Handler().postDelayed({
            startActivity(Intent(this, PostActivity::class.java))
            finish()
        },SPLASH_TIME_OUT)
    }


}