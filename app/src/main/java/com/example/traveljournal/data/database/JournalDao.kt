package com.example.traveljournal.data.database

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

interface JournalDao {
    @Query("SELECT * FROM journal")
    fun getAll(): LiveData<List<Journal>>

    @Query("SELECT * FROM journal WHERE id IN (:journalIds)")
    fun loadAllByIds(journalIds: IntArray): LiveData<List<Journal>>

    @Query("SELECT * FROM journal WHERE name LIKE :name  LIMIT 1")
    fun findByName(name: String): Journal

    @Insert
    fun insertAll(vararg journal: Journal)

    @Insert
    suspend fun insertJournal(vararg  journal:Journal)

    @Delete
    fun delete(journal: Journal)

    @Query("DELETE FROM journal")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM journal")
    fun getJournalsWithPages(): LiveData<List<JournalWithJournalPages>>

}