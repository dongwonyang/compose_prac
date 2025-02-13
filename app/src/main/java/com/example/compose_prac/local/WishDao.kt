package com.example.compose_prac.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WishDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wish: Wish)

    @Update
    suspend fun update(wish: Wish)

    @Delete
    suspend fun delete(wish: Wish)

    @Query("SELECT * FROM wish_table ORDER BY id ASC")
    fun getAllWishes(): Flow<List<Wish>>

    @Query("SELECT * FROM wish_table WHERE id = :id")
    suspend fun getWishById(id: Long): Wish?
}
