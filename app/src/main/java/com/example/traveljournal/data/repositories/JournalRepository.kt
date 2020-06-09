package com.example.traveljournal.data.repositories

import androidx.lifecycle.LiveData
import com.example.traveljournal.data.database.Journal
import com.example.traveljournal.data.database.JournalDao

class JournalRepository(private val journalDao: JournalDao) {
    val allJournals: LiveData<List<Journal>> = journalDao.getAll()

    suspend fun insert(journal: Journal) {
        journalDao.insertJournal(journal)
    }
}