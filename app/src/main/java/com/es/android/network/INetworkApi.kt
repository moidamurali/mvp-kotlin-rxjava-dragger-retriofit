package com.es.android.network

import com.es.android.data.MovieData
import com.es.android.data.PostData
import io.reactivex.Observable
import retrofit2.http.GET

interface INetworkApi {

    @GET(Endpoints.posts)
    fun getAllPosts(): Observable<List<PostData>>

    @GET(Endpoints.movies)
    fun getAllMovies(): Observable<List<MovieData>>
}