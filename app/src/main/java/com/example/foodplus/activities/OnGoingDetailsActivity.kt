package com.example.foodplus.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.foodplus.R
import com.example.foodplus.databinding.ActivityOnGoingDetailsBinding

class OnGoingDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOnGoingDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnGoingDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}