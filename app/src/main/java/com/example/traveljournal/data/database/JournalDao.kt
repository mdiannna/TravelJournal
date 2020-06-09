package com.example.traveljournal.data.database

import androidx.room.Insert
import androidx.room.Query

interface JournalDao {
    @Query("SELECT * FROM journal")
    fun getAll(): List<Journal>

    @Insert
    fun insertJournal(vararg  journal:Journal)

}