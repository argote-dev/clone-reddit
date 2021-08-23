package com.davidargote.clone_reddit.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class PostEntity(
    @PrimaryKey
    val id: String,
    val isVideo: Boolean,
    val url: String,
    val height: Int,
    val fallBackUrl: String,
    val ups: Int,
    val numComments: Int,
    val title: String,
    val subreddit: String,
    val created: Int,
    val domain: String,
    val subredditNamePrefixed: String,
    val saved: Boolean,
): Parcelable