package com.example.roomrecyclerview

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDao {

    @Query("SELECT * FROM total_table ORDER BY id DESC")
    fun getAllUser(): List<Student>

    @Query("SELECT * FROM total_table WHERE review_num > 5 and REPLACE(menu_name, ' ', '') LIKE '%' || :roll || '%' ORDER BY avr_star DESC,review_num DESC")
    fun findByRoll(roll: String): List<Student>
}
