//package com.example.traveljournal.data.database
//
//import androidx.room.Embedded
//import androidx.room.Entity
//import androidx.room.Relation
//
//@Entity
//class JournalWithJournalPages (
//    @Embedded val journal: Journal,
//    @Relation(
//        parentColumn = "journalId",
//        entityColumn = "journal_id"
//    )
//    val journalPages: List<JournalPage>
//
//)