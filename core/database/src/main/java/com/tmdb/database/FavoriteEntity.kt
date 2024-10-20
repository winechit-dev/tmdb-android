package com.tmdb.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_entity")
data class FavoriteEntity(
    @PrimaryKey val movieId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "created_at") val createdAt: Long,
)
