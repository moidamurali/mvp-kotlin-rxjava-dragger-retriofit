package com.es.developine.ui.movie

import com.es.developine.data.MovieData
import com.es.developine.data.PostData
import com.es.developine.ui.IView

interface MovieView: IView {

    fun showAllMovies(postList: List<MovieData>)
}