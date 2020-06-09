package com.example.traveljournal.data.database


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JournalPageDao {
    @Query("SELECT * FROM journal_pages")
    fun getAll(): LiveData<List<JournalPage>>

    @Query("SELECT * FROM journal_pages WHERE id IN (:journalPageIds)")
    fun loadAllByIds(journalPageIds: IntArray): LiveData<List<JournalPage>>

    @Query("SELECT * FROM journal_pages WHERE name LIKE :name  LIMIT 1")
    fun findByName(name: String): JournalPage

    @Query("SELECT * FROM journal_pages WHERE type LIKE :type  LIMIT 1")
    fun findByType(type: String): JournalPage

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJournalPage(vararg  journalPage:JournalPage)

    @Delete
    fun delete(journalPage: JournalPage)

    @Query("DELETE FROM journal_pages")
    suspend fun deleteAll() : Void

}