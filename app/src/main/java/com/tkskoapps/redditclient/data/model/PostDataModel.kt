package com.tkskoapps.redditclient.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PostDataModel(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("created_utc")
    val createdDate: Long = 0,
    @SerializedName("num_comments")
    val commentsCount: Long? = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as Long,
        parcel.readValue(Long::class.java.classLoader) as? Long
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(thumbnail)
        parcel.writeString(url)
        parcel.writeValue(createdDate)
        parcel.writeValue(commentsCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostDataModel> {
        override fun createFromParcel(parcel: Parcel): PostDataModel {
            return PostDataModel(parcel)
        }

        override fun newArray(size: Int): Array<PostDataModel?> {
            return arrayOfNulls(size)
        }
    }
}
