package com.example.compose_prac.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishRepository @Inject constructor(
    private val wishDao: WishDao
) {
    fun getAllWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    suspend fun insert(wish: Wish) = wishDao.insert(wish)
    suspend fun update(wish: Wish) = wishDao.update(wish)
    suspend fun delete(wish: Wish) = wishDao.delete(wish)
    suspend fun getWishById(id: Long) = wishDao.getWishById(id)
}
