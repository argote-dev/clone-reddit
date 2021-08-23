package com.davidargote.clone_reddit.repository.post_repository

import com.davidargote.clone_reddit.domain.local.PostEntity
import kotlinx.coroutines.flow.Flow

interface PostsRepositoryInterface {
    suspend fun getPost(): Flow<ArrayList<PostEntity>>
}