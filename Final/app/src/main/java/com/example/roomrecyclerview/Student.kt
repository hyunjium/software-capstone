package com.example.roomrecyclerview

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "total_table")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "menu_name") val firstName: String,
    @ColumnInfo(name = "store_name") val lastName: String,
    @ColumnInfo(name = "avr_star") val rollNo: Double,
    @ColumnInfo(name = "review_num") val reviewNum: Int,
    @ColumnInfo(name = "store_url") val storeUrl: String,
    @ColumnInfo(name = "store_star") val storeStar: Double
)