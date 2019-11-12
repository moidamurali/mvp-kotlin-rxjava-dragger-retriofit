package com.es.android.ui.posts

import com.es.android.data.PostData
import com.es.android.ui.IView

interface PostView: IView {

    fun showAllPosts(postList: List<PostData>)
}