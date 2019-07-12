package com.tkskoapps.redditclient.data.repositories

import com.tkskoapps.redditclient.data.model.PostListModel
import io.reactivex.Single

interface DataSource {

    fun getPosts(after: String? = null, type: String, count: String, limit: String): Single<PostListModel>

}