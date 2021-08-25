package com.davidargote.clone_reddit.data.remote

import com.davidargote.clone_reddit.domain.remote.TopResponse
import retrofit2.http.GET

interface Service {
    @GET("top.json")
    suspend fun getPost(): TopResponse
}