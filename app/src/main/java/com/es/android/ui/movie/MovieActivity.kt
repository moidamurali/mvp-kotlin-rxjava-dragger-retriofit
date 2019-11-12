package com.es.android.ui.movie

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.es.android.R
import com.es.android.data.MovieData
import com.es.android.ui.BaseActivity
import com.es.android.ui.adapters.MovieAdapter
import kotlinx.android.synthetic.main.activity_post.*

class MovieActivity : BaseActivity(), MovieView {


   var moviePresenter: MoviePresenterImpl?=null


    override fun setLayout(): Int {

        return R.layout.activity_post;
    }

    override fun init(savedInstanceState: Bundle?) {
      //  postPresenter.getAllPosts()
        getPresenter()?.let {
            it.getAllMovies()
        }
    }

    fun getPresenter(): MoviePresenterImpl?{
        moviePresenter = MoviePresenterImpl(this, application)
        return moviePresenter
    }






    override fun onStartScreen() {
    }

    override fun stopScreen() {
        moviePresenter?.let {
            moviePresenter!!.unbindView()
            moviePresenter = null
        }

    }


    override fun showAllMovies(postList: List<MovieData>) {
        //To change body of created functions use File | Settings | File Templates.
        Log.d("Response", "" + postList)
        rv_post_list.layoutManager = LinearLayoutManager(this)
        rv_post_list.adapter = MovieAdapter(postList, this)
    }


}
