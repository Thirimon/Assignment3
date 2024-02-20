package com.example.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment3.data.Student
import com.example.assignment3.databinding.ActivityViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewActivity : AppCompatActivity() {
    private lateinit var binding:ActivityViewBinding
    private lateinit var viewModel: StudentViewModel
    lateinit var student : Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //appDb=AppDatabase.getDatabase(this)
        binding=ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = StudentRepo(AppDatabase.getDatabase(applicationContext).studentDao())
        val viewModelFactory = ViewModelFactory(repository)
        viewModel= ViewModelProvider(this, viewModelFactory)[StudentViewModel::class.java]
        binding.queryBtn.setOnClickListener {
            if(binding.nameRd.isChecked){
                val name = binding.quDataIp.text.toString()
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
                val rollno = binding.quDataIp.text.toString()
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
    }

    private suspend fun displayData(student: Student){

        withContext(Dispatchers.Main){
            binding.viewRollNo.text= Editable.Factory.getInstance().newEditable(student.rollno)
            binding.viewName.text=Editable.Factory.getInstance().newEditable(student.name)
            binding.viewEmail.text=Editable.Factory.getInstance().newEditable(student.email)
            binding.viewPhone.text=Editable.Factory.getInstance().newEditable(student.phone)
            binding.viewAddress.text=Editable.Factory.getInstance().newEditable(student.address)


        }

    }

}