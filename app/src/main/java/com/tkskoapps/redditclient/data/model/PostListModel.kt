package com.tkskoapps.redditclient.data.model

import com.google.gson.annotations.SerializedName

data class PostListModel(
    @SerializedName("after")
    val lastItemId: String? = null,
    @SerializedName("before")
    val before: String? = null,
    @SerializedName("children")
    val posts: List<PostModel> = emptyList()
)
