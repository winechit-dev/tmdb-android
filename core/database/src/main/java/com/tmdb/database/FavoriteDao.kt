package com.tmdb.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_entity ORDER BY created_at DESC")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Insert
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("SELECT favorite FROM favorite_entity WHERE movieId = :movieId")
    fun isFavorite(movieId: Int): Flow<Boolean>

    @Query("DELETE FROM favorite_entity WHERE movieId = :movieId")
    suspend fun deleteAFavorite(movieId: Int)

    @Query("DELETE FROM favorite_entity")
    suspend fun deleteAllFavorites()
}