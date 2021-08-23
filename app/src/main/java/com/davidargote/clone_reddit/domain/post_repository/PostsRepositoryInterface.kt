package com.davidargote.clone_reddit.domain.post_repository

import com.davidargote.clone_reddit.data.local.PostEntity
import com.davidargote.clone_reddit.data.remote.Child
import kotlinx.coroutines.flow.Flow

interface PostsRepositoryInterface {
    suspend fun getPost(): Flow<ArrayList<PostEntity>>
}