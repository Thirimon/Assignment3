package com.example.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.insertBtn.setOnClickListener {
            val intent= Intent(this,InsertActivity::class.java)
            startActivity(intent)
        }
        binding.viewBtn.setOnClickListener {
            val intent= Intent(this,ViewActivity::class.java)
            startActivity(intent)
        }
        binding.updateBtn.setOnClickListener {
            val intent= Intent(this,UpdateActivity::class.java)
            startActivity(intent)
        }
        binding.deleteBtn.setOnClickListener {
            val intent= Intent(this,DeleteActivity::class.java)
            startActivity(intent)
        }
    }
}