package com.davidargote.clone_reddit.domain.remote

import com.google.gson.annotations.SerializedName

data class RedditVideo(
    @SerializedName("bitrate_kbps")
    val bitrateKbps: Int = 0,
    @SerializedName("fallback_url")
    val fallBackUrl: String = "",
    @SerializedName("width")
    val width: Int = 0,
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("duration")
    val duration: Int = 0,
    @SerializedName("is_gif")
    val isGif: Boolean = false,
    @SerializedName("transcoding_status")
    val transcodingStatus: String = ""
)
