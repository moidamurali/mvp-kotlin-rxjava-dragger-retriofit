package com.es.developine.network

import com.es.developine.data.MovieData
import com.es.developine.data.PostData
import com.google.gson.JsonElement
import io.reactivex.Observable
import retrofit2.http.GET

interface INetworkApi {

    @GET(Endpoints.posts)
    fun getAllPosts(): Observable<List<PostData>>

    @GET(Endpoints.movies)
    fun getAllMovies(): Observable<List<MovieData>>
}