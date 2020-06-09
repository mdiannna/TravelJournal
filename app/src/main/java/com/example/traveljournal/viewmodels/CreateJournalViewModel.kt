package com.example.traveljournal.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.traveljournal.data.database.AppDatabase
import com.example.traveljournal.data.database.Journal
import com.example.traveljournal.data.repositories.JournalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateJournalViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: JournalRepository

    val allJournals: LiveData<List<Journal>>

    init {
        val journalsDao = AppDatabase.getDatabase(application, viewModelScope).journalDao()
        repository = JournalRepository((journalsDao))
        allJournals = repository.allJournals
    }

    fun insert(journal: Journal) = viewModelScope.launch (Dispatchers.IO) {
        repository.insert(journal)
    }
}
