package com.example.foodplus.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.foodplus.R
import com.example.foodplus.databinding.ActivityMyRestaurantAcitivityBinding
import com.example.foodplus.databinding.ActivitySignInEmailBinding

class MyRestaurantAcitivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyRestaurantAcitivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRestaurantAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTooolbar()

        //Add Product
        binding.tvAddProduct.setOnClickListener {
            startActivity(Intent(this, MyRestaurantProductListActivity::class.java))
        }

    }

    private fun setTooolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // Add favorite button at toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_my_rastaurant_menu, menu)
        return true
    }
}