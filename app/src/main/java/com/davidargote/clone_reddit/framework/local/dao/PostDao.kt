package com.davidargote.clone_reddit.framework.local.dao

import androidx.room.*
import com.davidargote.clone_reddit.data.local.PostEntity

@Dao
interface PostDao {
    @Query("Select * FROM postentity")
    suspend fun getAllPost(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPost(movie: PostEntity)
}