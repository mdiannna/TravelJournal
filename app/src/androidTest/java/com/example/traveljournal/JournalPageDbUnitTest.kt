package com.example.traveljournal;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.traveljournal.data.database.AppDatabase
import com.example.traveljournal.data.database.JournalPage
import com.example.traveljournal.data.database.JournalPageDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException


/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
//@RunWith(MockitoJUnitRunner::class)
class JournalPageDbUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var journalPageDao: JournalPageDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        journalPageDao = db.journalPageDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetJournalPage() {
        runBlocking {
            val journalPage = JournalPage(1, "Page1", 23.2, 45.3, "test", "-", 1)
            journalPageDao.insertJournalPage(journalPage)
            val allJournalPages = journalPageDao.getAll().waitForValue()
            assertEquals(allJournalPages[0].name, journalPage.name)
        }
    }

    @Test
    @Throws(Exception::class)
    fun getAllJournals() {
        runBlocking {
            val journalPage = JournalPage(2, "Page2", 22.2, 45.3, "test", "-", 1)
            journalPageDao.insertJournalPage(journalPage)
            val journalPage2 = JournalPage(3, "Page3", 22.2, 45.3, "test", "-", 1)
            journalPageDao.insertJournalPage(journalPage2)
            val allJournalPages = journalPageDao.getAll().waitForValue()
            assertEquals(allJournalPages[0].name, journalPage.name)
            assertEquals(allJournalPages[1].name, journalPage2.name)
        }
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() {
        runBlocking {

            val journalPage = JournalPage(2, "Page2", 22.2, 45.3, "test", "-", 1)
            journalPageDao.insertJournalPage(journalPage)
            val journalPage2 = JournalPage(3, "Page3", 22.2, 45.3, "test", "-", 1)
            journalPageDao.insertJournalPage(journalPage2)
            journalPageDao.deleteAll()
            val allJournalPages = journalPageDao.getAll().waitForValue()
            assertTrue(allJournalPages.isEmpty())
        }
    }
}
