package com.example.traveljournal.data.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="journal_pages")
class JournalPage (
    @PrimaryKey val id: Int,
    @ColumnInfo(name= "name") val name:String,
    @ColumnInfo(name= "lat") val lat:Double?,
    @ColumnInfo(name= "lng") val lng:Double?,
    @ColumnInfo(name= "type") val type:String?,
    @ColumnInfo(name= "image") val image:String?,
    val journalId : Long
)