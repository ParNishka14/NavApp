package com.loxick.navapp

import android.content.Context
import android.util.Log
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(
    entities = [
        Users::class
    ],
    version = 2
)
abstract class MainDB : RoomDatabase() {
    abstract fun itemDao(): DaoA
    companion object{
        fun createDataBase(context: Context):MainDB{
            Log.d("Info:::","DBCreate")
            return Room.databaseBuilder(
                context,
                MainDB::class.java,
                "test.db"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
}
