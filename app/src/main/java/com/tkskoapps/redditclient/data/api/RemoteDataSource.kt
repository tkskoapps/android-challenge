package com.tkskoapps.redditclient.data.api

import com.tkskoapps.redditclient.data.model.PostListModel
import com.tkskoapps.redditclient.data.repositories.DataSource
import io.reactivex.Single

open class RemoteDataSource(
    private val redditAPI: RedditAPI
) : DataSource {

    override fun getPosts(after: String?, type: String, count: String, limit: String): Single<PostListModel> {
        return redditAPI.getPosts(after, type, count, limit).map {
            it.postList
        }
    }

}
