package com.davidargote.clone_reddit.framework.remote

import com.davidargote.clone_reddit.data.remote.TopResponse
import retrofit2.http.GET

interface Service {
    @GET("top.json")
    suspend fun getPost(): TopResponse
}