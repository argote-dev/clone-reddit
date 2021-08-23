package com.davidargote.clone_reddit.repository.post_repository

import com.davidargote.clone_reddit.core.InternetChek
import com.davidargote.clone_reddit.domain.local.PostEntity
import com.davidargote.clone_reddit.domain.remote.toPostEntity
import com.davidargote.clone_reddit.domain.remote.toPostEntityVideo
import com.davidargote.clone_reddit.framework.local.dao.PostDao
import com.davidargote.clone_reddit.framework.remote.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val service: Service,
    private val local: PostDao
) : PostsRepositoryInterface {

    override suspend fun getPost(): Flow<ArrayList<PostEntity>> = flow {
        if (InternetChek.isNetworkAvailable()) {
            val response = service.getPost()
            response.data.children.forEach { child ->
                val post = child.data
                if (post.isVideo) {
                    local.insertPost(post.toPostEntityVideo())
                } else {
                    local.insertPost(post.toPostEntity())
                }
            }
            emit(local.getAllPost())
        } else {
            emit(local.getAllPost())
        }
    }.flowOn(Dispatchers.IO) as Flow<ArrayList<PostEntity>>

}