package com.davidargote.clone_reddit.data.remote

import com.davidargote.clone_reddit.data.local.PostEntity
import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("subreddit")
    val subreddit: String? = "",
    @SerializedName("saved")
    val saved: Boolean = false,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("subreddit_name_prefixed")
    val subredditNamePrefixed: String? = "",
    @SerializedName("created")
    val created: Int = 0,
    @SerializedName("domain")
    val domain: String? = "",
    @SerializedName("url_overridden_by_dest")
    val urlOverriddenByDest: String = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("media")
    val media: Media,
    @SerializedName("is_video")
    val isVideo: Boolean = false,
    @SerializedName("num_comments")
    val numComments: Int = 0,
    @SerializedName("ups")
    val ups: Int = 0
)

fun Post.toPostEntityVideo(): PostEntity = PostEntity(
    isVideo = this.isVideo,
    url = this.url ?: "",
    height = this.media.redditVideo.height,
    fallBackUrl = this.media.redditVideo.fallBackUrl,
    ups = this.ups,
    numComments = this.numComments,
    title = this.title ?: "",
    subreddit = this.subreddit ?: "",
    created = this.created,
    domain = this.domain ?: "",
    subredditNamePrefixed = this.subredditNamePrefixed ?: "",
    saved = this.saved,
    id = this.id ?: ""
)

fun Post.toPostEntity(): PostEntity = PostEntity(
    isVideo = this.isVideo,
    url = this.url ?: "",
    height = 0,
    fallBackUrl = "",
    ups = this.ups,
    numComments = this.numComments,
    title = this.title ?: "",
    subreddit = this.subreddit ?: "",
    created = this.created,
    domain = this.domain ?: "",
    subredditNamePrefixed = this.subredditNamePrefixed ?: "",
    saved = this.saved,
    id = this.id ?: ""
)