package com.example.traveljournal.data.repositories

import androidx.lifecycle.LiveData
import com.example.traveljournal.data.database.JournalPage
import com.example.traveljournal.data.database.JournalPageDao

class JournalPageRepository(private val journalPageDao: JournalPageDao) {
    val allJournals: LiveData<List<JournalPage>> = journalPageDao.getAll()

    suspend fun insert(journalPage: JournalPage) {
        journalPageDao.insertJournalPage(journalPage)
    }
}