package com.davidargote.clone_reddit.domain.remote

import com.google.gson.annotations.SerializedName

data class Child(
    @SerializedName("kind")
    val kind: String = "",
    @SerializedName("data")
    val data: Post
)