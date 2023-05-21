package com.example.roomdata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDao {

    @Query("SELECT * FROM student_table")
    fun getAll(): LiveData<List<Student>>

    @Query("SELECT * FROM student_table WHERE roll_no LIKE :roll LIMIT 1")
    fun findByRoll(roll: Int): Student

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(student: Student)

    @Delete
    fun delete(student: Student)

    @Query("DELETE FROM student_table")
    fun deleteAll()
}