package com.example.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.assignment3.data.Student
import com.example.assignment3.databinding.ActivityInsertBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InsertActivity : AppCompatActivity() {
    private lateinit var binding:ActivityInsertBinding
    private lateinit var viewModel: StudentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //appDb=AppDatabase.getDatabase(this)
        binding=ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = StudentRepo(AppDatabase.getDatabase(applicationContext).studentDao())
        val viewModelFactory = ViewModelFactory(repository)
        viewModel= ViewModelProvider(this, viewModelFactory)[StudentViewModel::class.java]
        binding.saveBtn.setOnClickListener {
            writeData()
        }
    }

    private fun writeData() {
        val rollNo=binding.rollNo.text.toString()
        val stuName=binding.stuName.text.toString()
        val email=binding.emailInput.text.toString()
        val phone=binding.phoneInput.text.toString()
        val address=binding.address.text.toString()
        if(rollNo.isNotEmpty() && stuName.isNotEmpty()&&email.isNotEmpty()&&phone.isNotEmpty()&&address.isNotEmpty()){
            val stu=Student(null,rollNo,stuName,email,phone,address)
            GlobalScope.launch(Dispatchers.IO) {
                viewModel.insertStudent(stu)
            }
            binding.rollNo.text.clear()
            binding.stuName.text.clear()
            binding.emailInput.text.clear()
            binding.phoneInput.text.clear()
            binding.address.text.clear()
            Toast.makeText(this,"Successfully save",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"Please enter data",Toast.LENGTH_LONG).show()
        }
    }
}