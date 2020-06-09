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



public class JournalViewModel(application: Application):AndroidViewModel(application) {

    private val repository: JournalRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    public lateinit var allJournals: LiveData<List<Journal>>

    init {
        val wordsDao = AppDatabase.getDatabase(application, viewModelScope).journalDao()
        repository = JournalRepository(wordsDao)
        allJournals = repository.allJournals
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(journal: Journal) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(journal)
    }
}
