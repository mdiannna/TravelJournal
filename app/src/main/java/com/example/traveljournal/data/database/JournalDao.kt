package com.example.traveljournal.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JournalDao {
    @Query("SELECT * FROM journals")
    fun getAll(): LiveData<List<Journal>>

//    @Query("SELECT * FROM journal WHERE id IN (:journalIds)")
//    fun loadAllByIds(journalIds: IntArray): LiveData<List<Journal>>

    @Query("SELECT * FROM journals WHERE name LIKE :name  LIMIT 1")
    fun findByName(name: String): Journal

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertAll(vararg journal: Journal)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJournal(vararg  journal:Journal)

    @Delete
    fun delete(journal: Journal)

    @Query("DELETE FROM journals")
    suspend fun deleteAll() : Void
}