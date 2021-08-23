package com.davidargote.clone_reddit.domain.remote

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("after")
    val after:String = "",
    @SerializedName("dist")
    val dist: Int = 0,
    @SerializedName("modhash")
    val modHash: String = "",
    @SerializedName("geo_filter")
    val geoFilter:String = "",
    @SerializedName("children")
    val children: ArrayList<Child>,
    @SerializedName("before")
    val before: String? = null
)
