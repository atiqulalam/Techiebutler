package com.techiebutler
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

import com.google.gson.annotations.SerializedName

@Parcelize
data class PostDto(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("userId")
    val userId: Long? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("body")
    val body: String? = null,
    @SerializedName("customData")
    var customData: String? = null
):Parcelable