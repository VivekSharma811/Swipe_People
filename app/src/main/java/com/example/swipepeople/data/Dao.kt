package com.example.swipepeople.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swipepeople.data.model.ArchivedUser

@Dao
interface Dao {

    @Query("SELECT * FROM People")
    fun getArchivedPeople(): LiveData<List<ArchivedUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(archivedUser: ArchivedUser)
}