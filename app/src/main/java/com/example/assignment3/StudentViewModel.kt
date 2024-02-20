package com.example.assignment3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment3.data.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(
    private val userRepository: StudentRepo
) :ViewModel(){
    fun insertStudent(student: Student) {
       viewModelScope.launch {
           userRepository.insertStudent(student)
       }
    }
    suspend fun getStudentByName(name:String):Student{

        return userRepository.getStudentbyName(name)
    }
    suspend fun getStudentByRollno(rollno:String):Student{

        return userRepository.getStudentbyRollno(rollno)
    }
    fun updateStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateStudent(student)
        }
    }
    fun deleteStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO){
            userRepository.deleteStudent(student)
        }
    }
}