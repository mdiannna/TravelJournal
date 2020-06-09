package com.example.traveljournal.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Journal(
    @PrimaryKey
    @ColumnInfo(name= "name") val name:String?
)