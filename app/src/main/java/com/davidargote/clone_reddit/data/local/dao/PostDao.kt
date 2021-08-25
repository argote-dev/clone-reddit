package com.davidargote.clone_reddit.data.local.dao

import androidx.room.*
import com.davidargote.clone_reddit.domain.local.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM postentity")
    suspend fun getAllPost(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(movie: PostEntity)

    @Query("DELETE FROM postentity")
    suspend fun deleteAllPost()
}