package com.example.traveljournal.data.database;

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Journal::class, JournalPage::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

        abstract fun journalDao(): JournalDao
        abstract fun journalPageDao(): JournalPageDao

        private class JournalDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

                override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        INSTANCE?.let { database ->
                                scope.launch {
                                        var journalDao = database.journalDao()
                                        var journalPageDao = database.journalPageDao()


                                        // Delete all content here.
                                        journalDao.deleteAll()
                                        journalPageDao.deleteAll()

                                        // Add sample words.
                                        var journal = Journal("Journal From Paris")
                                        journalDao.insertJournal(journal)
                                        journal = Journal("UK Journal")
                                        journalDao.insertJournal(journal)

                                        journal = Journal("Journal Moldova")
                                        journalDao.insertJournal(journal)
                                }
                        }
                }
        }

        companion object {
                @Volatile
                private var INSTANCE: AppDatabase? = null

                fun getDatabase(
                        context : Context,
                        scope: CoroutineScope
                ): AppDatabase {
                        // if the INSTANCE is not null, then return it,
                        // if it is, then create the database
                        return INSTANCE ?: synchronized(this) {
                                val instance = Room.databaseBuilder(
                                        context.applicationContext,
                                        AppDatabase::class.java,
                                        "journals_database"
                                )
                                        .addCallback(JournalDatabaseCallback(scope))
                                        .fallbackToDestructiveMigration() // lose all data before migration
                                        .build()
                                INSTANCE = instance
                                // return instance
                                instance
                        }
                }
        }
}