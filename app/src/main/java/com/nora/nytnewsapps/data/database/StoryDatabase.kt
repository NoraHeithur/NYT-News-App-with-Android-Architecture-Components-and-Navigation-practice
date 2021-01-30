package com.nora.nytnewsapps.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE_FILE_NAME = "SAVED_NEWS_DATABASE"

@Database(entities = [DatabaseStory::class], version = 1, exportSchema = false)
abstract class StoryDatabase: RoomDatabase() {
    abstract val storyDao: StoryDao
}

private lateinit var INSTANCE: StoryDatabase

        fun getInstance(context: Context): StoryDatabase {
            synchronized(StoryDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        StoryDatabase::class.java,
                        DATABASE_FILE_NAME).build()
                }
                return INSTANCE
            }
        }