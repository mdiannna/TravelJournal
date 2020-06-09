package com.example.traveljournal.data.database


import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

interface JournalPageDao {
    @Query("SELECT * FROM journalpage")
    fun getAll(): LiveData<List<JournalPage>>

    @Query("SELECT * FROM journalpage WHERE id IN (:journalPageIds)")
    fun loadAllByIds(journalPageIds: IntArray): LiveData<List<JournalPage>>

    @Query("SELECT * FROM journalpage WHERE name LIKE :name  LIMIT 1")
    fun findByName(name: String): JournalPage

    @Query("SELECT * FROM journalpage WHERE type LIKE :type  LIMIT 1")
    fun findByType(type: String): JournalPage

    @Insert
    suspend fun insertJournalPage(vararg  journalPage:JournalPage)

    @Delete
    fun delete(journalPage: JournalPage)

    @Query("DELETE FROM journalpage")
    suspend fun deleteAll()

}