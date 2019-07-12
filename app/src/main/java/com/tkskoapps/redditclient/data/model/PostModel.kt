package com.tkskoapps.redditclient.data.model

import com.google.gson.annotations.SerializedName

data class PostModel(
    @SerializedName("data")
    val postData: PostDataModel,
    var read: Boolean = false
)