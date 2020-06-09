package com.example.traveljournal.data.database;

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Journal::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): JournalDao
}

//Usage:
//val db = Room.databaseBuilder(
//        applicationContext,
//        AppDatabase::class.java, "database-name"
//).build()
