package com.example.foodplus.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.example.foodplus.R
import com.example.foodplus.activities.RegisterRestaurantActivity

class FragmentProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //if the user have registered as business account, then the button text into
        val btnConnectedRestaurant = view.findViewById<AppCompatButton>(R.id.btConnectRestaurant)
        btnConnectedRestaurant.setOnClickListener {
            val intent = Intent(context, RegisterRestaurantActivity::class.java)
        }
    }

}