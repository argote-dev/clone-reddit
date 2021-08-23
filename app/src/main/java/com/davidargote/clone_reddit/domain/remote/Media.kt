package com.davidargote.clone_reddit.domain.remote

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("reddit_video")
    val redditVideo: RedditVideo
)