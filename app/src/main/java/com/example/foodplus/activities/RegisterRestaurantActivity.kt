package com.example.foodplus.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.foodplus.R
import com.example.foodplus.databinding.ActivityRegisterRestaurantBinding

class RegisterRestaurantActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterRestaurantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

        val btRegister = findViewById<AppCompatButton>(R.id.btRegister)
        btRegister.setOnClickListener {
            val intent = Intent(this, MyRestaurantAcitivity::class.java)
            startActivity(intent)
        }
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}