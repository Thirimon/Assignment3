package com.example.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.assignment3.data.Student
import com.example.assignment3.databinding.ActivityDeleteBinding
import com.example.assignment3.databinding.ActivityUpdateBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var viewModel: StudentViewModel
    lateinit var student : Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = StudentRepo(AppDatabase.getDatabase(applicationContext).studentDao())
        val viewModelFactory = ViewModelFactory(repository)
        viewModel= ViewModelProvider(this, viewModelFactory)[StudentViewModel::class.java]
        binding.showInUpdate.setOnClickListener {
            if(binding.nameRd.isChecked){
                val name = binding.quInfoInUpdate.text.toString()
                if (name.isNotEmpty()){
                    GlobalScope.launch {

                        student = viewModel.getStudentByName(name)
                        Log.d("Robin Data",student.toString())
                        if(student!=null)
                        {  displayData(student)}
                        else{
                            Toast.makeText(applicationContext,"Please enter the data", Toast.LENGTH_SHORT).show()
                        }

                    }

                }else Toast.makeText(this,"Please enter the data", Toast.LENGTH_SHORT).show()
            }
            else if(binding.rollRd.isChecked){
                val rollno = binding.quInfoInUpdate.text.toString()
                if (rollno.isNotEmpty()){
                    GlobalScope.launch {

                        student = viewModel.getStudentByRollno(rollno)
                        Log.d("Robin Data",student.toString())
                        if(student!=null)
                        {  displayData(student)}
                        else{
                            Toast.makeText(applicationContext,"Please enter the data", Toast.LENGTH_SHORT).show()
                        }

                    }

                }else Toast.makeText(this,"Please enter the data", Toast.LENGTH_SHORT).show()
            }
        }
        binding.delete.setOnClickListener {
//            student.rollno=binding.updateRollNo.text.toString()
//            student.name=binding.upName.text.toString()
//            student.email=binding.upEmail.text.toString()
//            student.address=binding.upAddress.text.toString()
//            student.phone=binding.upPhone.text.toString()
            viewModel.deleteStudent(student)
            Toast.makeText(this,"Delete successfully", Toast.LENGTH_SHORT).show()
        }
    }
    private suspend fun displayData(student: Student){

        withContext(Dispatchers.Main){

            binding.updateRollNo.text= Editable.Factory.getInstance().newEditable(student.rollno)
            binding.upName.text= Editable.Factory.getInstance().newEditable(student.name)
            binding.upEmail.text= Editable.Factory.getInstance().newEditable(student.email)
            binding.upPhone.text= Editable.Factory.getInstance().newEditable(student.phone)
            binding.upAddress.text= Editable.Factory.getInstance().newEditable(student.address)


        }
    }
    }
