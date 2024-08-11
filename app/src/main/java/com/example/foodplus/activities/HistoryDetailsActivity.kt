package com.example.foodplus.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foodplus.R
import com.example.foodplus.databinding.ActivityHistoryDetailsBinding

class HistoryDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHistoryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
        onBackPressed()
        return true
    }
}