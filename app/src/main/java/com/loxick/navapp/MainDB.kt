package com.loxick.navapp

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(
    entities = [
        Users::class
    ],
    version = 1
)
abstract class MainDB : RoomDatabase() {
    abstract val dao: com.loxick.navapp.Dao
    companion object{
        fun createDataBase(context: Context):MainDB{
            return Room.databaseBuilder(
                context,
                MainDB::class.java,
                "test.db"
            ).build()
        }
    }
}
