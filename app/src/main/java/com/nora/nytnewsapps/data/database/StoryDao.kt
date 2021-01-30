package com.nora.nytnewsapps.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StoryDao {

    @Query("SELECT * FROM story_table")
    fun getStories(): LiveData<List<DatabaseStory>>

    @Query("SELECT * FROM story_table")
    fun getSavedStories(): LiveData<List<DatabaseStory>>

    @Query("DELETE FROM story_table WHERE id = :id")
    fun deleteStory(id: Long): DatabaseStory

    @Insert
    fun insert(vararg databaseStory: DatabaseStory)
}