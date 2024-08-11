
package com.example.foodplus.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.example.foodplus.R
import com.example.foodplus.activities.HistoryDetailsActivity


class HistoryOrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cvHistoryItem : CardView = view.findViewById(R.id.cvHistoryItem)
        cvHistoryItem.setOnClickListener {
            val intent = Intent(context, HistoryDetailsActivity::class.java)
            startActivity(intent)
        }
    }

}