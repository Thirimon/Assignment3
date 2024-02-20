package com.example.assignment3

import com.example.assignment3.data.Student

class StudentRepo (
    private val userDao: StudentDao
    ) {

    suspend fun insertStudent(student: Student) {
        userDao.insert(student)
    }

    suspend fun getStudentbyName(name: String): Student {
        return userDao.getStudentbyName(name)

    }

    suspend fun getStudentbyRollno(rollno: String): Student {
        return userDao.getStudentbyRollNo(rollno)

    }

    suspend fun updateStudent(student: Student) {
        student.rollno?.let { student.name?.let { it1 -> student.email?.let { it2 ->
            student.phone?.let { it3 ->
                student.address?.let { it4 ->
                    userDao.update(it1,
                        it2, it3, it4,student.rollno)
                }
            }
        } } }
    }

    suspend fun deleteStudent(student: Student) {
        student.rollno?.let { userDao.delete(it) }
    }

}
