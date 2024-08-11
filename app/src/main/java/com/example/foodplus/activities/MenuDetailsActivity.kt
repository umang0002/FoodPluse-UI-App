package com.example.foodplus.activities

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.foodplus.R
import com.example.foodplus.databinding.ActivityMenuDetailsBinding
import com.example.foodplus.models.MenuData

class MenuDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMenuDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

        // Get Data with intent and set to view
        val menuData = intent.getParcelableExtra<MenuData>("OBJECT_INTENT")
        binding.tvMenuTitle.text = menuData?.name
        binding.tvStockAmount.text = "${menuData?.stock} left"
        binding.tvDescription.text = menuData?.description
        binding.tvPriceNormal.text = "Rs${menuData?.normalPrice}"
        binding.tvTitlePrice.paintFlags = binding.tvPriceNormal.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        binding.tvExpire.text = menuData?.expireDate
        binding.ivMenuImage.setImageResource(menuData!!.image)

        binding.btAdd.setOnClickListener {
            finish()
        }
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}