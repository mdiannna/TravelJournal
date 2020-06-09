package com.example.traveljournal.data.database;

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Journal::class), version = 1, exportSchema = false)
public abstract class AppDatabase : RoomDatabase() {
        abstract fun journalDao(): JournalDao

        private class AppDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

                override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        INSTANCE?.let{ database ->
                                scope.launch {
                                        populateDatabase(database.journalDao())
                                }
                        }
                }

                suspend fun populateDatabase(journalDao: JournalDao) {
                        journalDao.deleteAll()

                        var journal = Journal("Journal1")
                        journalDao.insertJournal(journal)

                }
        }

        companion object {
                //Singleton prevents multiple instances off database openint
                // at the same time
                @Volatile
                private var INSTANCE: AppDatabase? = null

                fun getDatabase(context:Context, scope: CoroutineScope): AppDatabase {
                        val tempInstance = INSTANCE
                        if (tempInstance!=null) {
                                return tempInstance
                        }

                        synchronized(this) {
                                val instance = Room.databaseBuilder(
                                        context.applicationContext,
                                        AppDatabase::class.java,
                                        "journals_database"
                                ).addCallback(AppDatabaseCallback(scope)).build()
                                INSTANCE = instance
                                return instance
                        }
                }
        }


}

//Usage:
//val db = Room.databaseBuilder(
//        applicationContext,
//        AppDatabase::class.java, "database-name"
//).build()
