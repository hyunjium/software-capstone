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

    @Query("SELECT * FROM total_table WHERE review_num > 9 and REPLACE(menu_name, ' ', '') LIKE '%' || REPLACE(:roll,' ','') || '%' ORDER BY avr_star DESC,review_num DESC")
    fun findByRoll(roll: String): List<Student>

    @Query("SELECT * FROM total_table WHERE review_num > 9 and REPLACE(store_name, ' ', '') LIKE '%' || REPLACE(:roll,' ','') || '%' ORDER BY avr_star DESC,review_num DESC")
    fun findByRoll2(roll: String): List<Student>

    @Query("SELECT * FROM total_table WHERE review_num > 9 and REPLACE(menu_name, ' ', '') LIKE '%' || REPLACE(:roll,' ','') || '%' ORDER BY review_num DESC,avr_star DESC")
    fun findByRoll3(roll: String): List<Student>

    @Query("SELECT * FROM total_table WHERE review_num > 9 and REPLACE(store_name, ' ', '') LIKE '%' || REPLACE(:roll,' ','') || '%' ORDER BY review_num DESC,avr_star DESC")
    fun findByRoll4(roll: String): List<Student>
}
