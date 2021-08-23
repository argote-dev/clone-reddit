package com.davidargote.clone_reddit.framework.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.davidargote.clone_reddit.core.Constants
import com.davidargote.clone_reddit.data.local.PostEntity
import com.davidargote.clone_reddit.framework.local.dao.PostDao

@Database(entities = [PostEntity::class], version = 1)
abstract class ApplicationDatabase: RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                ApplicationDatabase::class.java,
                Constants.NAME_LOCAL_DB
            ).fallbackToDestructiveMigration().build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}