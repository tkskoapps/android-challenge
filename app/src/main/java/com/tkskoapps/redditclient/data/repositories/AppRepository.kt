package com.tkskoapps.redditclient.data.repositories

import com.tkskoapps.redditclient.data.model.PostListModel
import io.reactivex.Single

class AppRepository(private val dataSource: DataSource) {

    fun getPosts(after: String? = null, type: String, count: String, limit: String): Single<PostListModel> {
        return dataSource.getPosts(after, type, count, limit)
    }

}
