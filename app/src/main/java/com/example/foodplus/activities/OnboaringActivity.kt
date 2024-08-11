package com.example.foodplus.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.foodplus.R
import com.example.foodplus.adapter.PagerAdapter
import com.example.foodplus.databinding.ActivityOnboaringBinding
import com.example.foodplus.models.OnBoardingData

class OnboaringActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOnboaringBinding
    private var itemList = ArrayList<OnBoardingData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboaringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewPager()

    }

    private fun setUpViewPager() {
        itemList = getItems()
        binding.viewPager.adapter = PagerAdapter(itemList)
        binding.wormDot.attachTo(binding.viewPager)
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private var pageChangeCallback : ViewPager2.OnPageChangeCallback = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            if (position  == itemList.size -1) {
                binding.tvNext.text = "Let's save the food"
            } else {
                binding.tvNext.text = "Next!"
            }
        }
    }

    private fun getItems() : ArrayList<OnBoardingData> {
        val items = ArrayList<OnBoardingData> ()

        items.add(
            OnBoardingData(
                "Let's together save food",
                "Every food that is wasted can damage\n" +
                        "the environment and our planet so\n" +
                        "Let's together save the food ",
                R.drawable.il_motherearthdaypana
            )
        )

        items.add(
            OnBoardingData(
                "Get tasty food affordably!",
                "This app will help yo find\n" +
                        "delicious food at affordable prices while\n" +
                        "protecting the environment",
                R.drawable.il_shawarmapana
            )
        )

        items.add(
            OnBoardingData(
                "Find the best meal near you",
                "You can find the best meal  with best deals\n" +
                        "near you, pay in the app and\n" +
                        "pick up your food",
                R.drawable.il_orderfoodpana
            )
        )
        return items
    }

    fun onClick(v: View) {
        when(v) {
            binding.tvNext -> {
                val currentItemPosition = binding.viewPager.currentItem
                if (currentItemPosition == itemList.size - 1) {
                    val intent = Intent(this@OnboaringActivity, SignInActivity::class.java)
                    startActivity(intent)
                    return
                }
                binding.viewPager.setCurrentItem(currentItemPosition + 1, true)
            }
        }
    }

}