package com.example.traveljournal.data.database.migrations

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.traveljournal.data.database.AppDatabase

class JournalMigrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                    "PRIMARY KEY(`id`))")
        }
    }

    fun migrate(applicationContext: Context) {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").addMigrations(MIGRATION_1_2).build()
    }

}
