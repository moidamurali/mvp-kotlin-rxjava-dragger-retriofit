package com.es.android.ui.movie

import com.es.android.data.MovieData
import com.es.android.ui.IView

interface MovieView: IView {

    fun showAllMovies(postList: List<MovieData>)
}