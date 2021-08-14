package com.example.swipepeople.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.swipepeople.data.model.ArchivedUser

@Database(entities = [ArchivedUser::class], version = 1, exportSchema = false)
abstract class PeopleDb : RoomDatabase() {

    abstract fun dao(): Dao

    companion object {
        @Volatile
        private var instance: PeopleDb? = null

        fun getDatabase(context: Context): PeopleDb = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, PeopleDb::class.java, "PeopleDb")
                .fallbackToDestructiveMigration()
                .build()
    }
}