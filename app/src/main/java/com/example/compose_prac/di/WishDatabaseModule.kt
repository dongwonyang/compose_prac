package com.example.compose_prac.di

import android.content.Context
import androidx.room.Room
import com.example.compose_prac.local.WishDao
import com.example.compose_prac.local.WishDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ViewModelComponent::class)
@Module
class WishDatabaseModule {
    @Provides
    fun provideWishDatabase(@ApplicationContext context: Context): WishDatabase {
        return Room.databaseBuilder(
            context,
            WishDatabase::class.java,
            "wish_database"
        ).build()
    }

    @Provides
    fun provideWishDao(database: WishDatabase): WishDao {
        return database.wishDao()
    }
}


