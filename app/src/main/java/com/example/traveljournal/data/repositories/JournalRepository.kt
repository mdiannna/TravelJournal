package com.example.traveljournal.data.repositories

import androidx.lifecycle.LiveData
import com.example.traveljournal.data.database.Journal
import com.example.traveljournal.data.database.JournalDao

import androidx.annotation.WorkerThread

class JournalRepository(private val journalDao: JournalDao) {
    val allJournals: LiveData<List<Journal>> = journalDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(journal: Journal) {
        journalDao.insertJournal(journal)
    }

}