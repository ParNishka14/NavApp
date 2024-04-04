package com.loxick.navapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoA {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertItem(users: Users)
    @Delete
    suspend fun DeleteItem(users: Users)
    @Update
    suspend fun UpdateItem(users: Users)
    @Query("SELECT * FROM usertTable")
    fun getAllItems(): Flow<List<Users>>
    @Query("DELETE FROM usertTable")
    fun deleteAllUsers(): Int

}