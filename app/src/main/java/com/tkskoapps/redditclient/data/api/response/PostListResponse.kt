package com.tkskoapps.redditclient.data.api.response

import com.google.gson.annotations.SerializedName
import com.tkskoapps.redditclient.data.model.PostListModel

data class PostListResponse(
    @SerializedName("data")
    val postList: PostListModel
)