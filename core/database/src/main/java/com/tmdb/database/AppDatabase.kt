package com.tmdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory


@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        fun buildDatabase(context: Context): AppDatabase {
            val userEnteredPassphrase = BuildConfig.PASS_PHRASE.toCharArray()
            val passphrase: ByteArray = SQLiteDatabase.getBytes(userEnteredPassphrase)
            val factory = SupportFactory(passphrase)
            return Room.databaseBuilder(context, AppDatabase::class.java, "movie-db")
                .openHelperFactory(factory)
                .build()
        }
    }
}