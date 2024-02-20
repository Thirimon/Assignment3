package com.example.assignment3

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment3.data.Student

@Dao
interface StudentDao {
    @Query("Select rollNumber,name,email,phone,address from student_table where rollNumber LIKE:rollNo ")
    fun getStudentbyRollNo(rollNo:String):Student
    @Query("Select rollNumber,name,email,phone,address from student_table where name LIKE:name ")
    fun getStudentbyName(name:String):Student
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: Student)
//    @Update
//    suspend fun update(student: Student)
    @Query("UPDATE student_table SET name=:name,email=:email,phone=:phone,address=:address WHERE rollNumber LIKE:rollNumber")
    suspend fun update( name: String, email:String, phone:String, address:String,rollNumber:String)

    @Query("DELETE from student_table where rollNumber LIKE:rollNumber")
    suspend fun delete(rollNumber: String)
}