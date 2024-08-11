package com.example.foodplus.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodplus.fragments.CartOrderFragment
import com.example.foodplus.fragments.HistoryOrderFragment
import com.example.foodplus.fragments.OnGoingOrdersFragment

class PagerOrdersAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 3


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> { CartOrderFragment() }
            1 -> { OnGoingOrdersFragment() }
            2 -> { HistoryOrderFragment() }
            else -> {throw Resources.NotFoundException("position not found")}
        }
    }
}