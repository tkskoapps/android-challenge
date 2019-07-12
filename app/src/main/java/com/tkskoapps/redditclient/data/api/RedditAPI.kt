package com.tkskoapps.redditclient.data.api

import com.tkskoapps.redditclient.data.api.response.PostListResponse
import com.tkskoapps.redditclient.ui.utils.AppConstants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditAPI {

    @GET(AppConstants.EndPoint.GET_TOP_POSTS)
    fun getPosts(
        @Query("after") after: String? = null,
        @Query("t") type: String,
        @Query("count") count: String,
        @Query("limit") limit: String
    ): Single<PostListResponse>

}
