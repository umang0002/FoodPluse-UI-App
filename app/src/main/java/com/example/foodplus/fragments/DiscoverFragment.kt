
package com.example.foodplus.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplus.R
import com.example.foodplus.activities.RestaurantDetailsActivity
import com.example.foodplus.adapter.RestaurantAdapter
import com.example.foodplus.models.RestaurantData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class DiscoverFragment : Fragment(), OnMapReadyCallback, OnMarkerClickListener,
    GoogleMap.OnInfoWindowClickListener {

        private var isPermissionGranted : Boolean = false
    private var myLocationButton : View? = null

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.isMyLocationEnabled = true
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-7.7796278208676775, 110.42692899786957), 14f))

        //get data from string resource
        val locName = resources.getStringArray(R.array.loc_name)
        val lat = resources.getStringArray(R.array.latitude)
        val long = resources.getStringArray(R.array.longitude)

        //Display restaurant marker
        for (i in locName.indices) {
            googleMap.addMarker( MarkerOptions()
                .position(LatLng(lat[i].toDouble(), long[i].toDouble()))
                .title(locName[i])
                .icon(BitmapFromVector(requireContext(), R.drawable.ic_restaurant_location_open)))
        }

        googleMap.setOnInfoWindowClickListener { this }
    }

    private fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId )
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.minimumHeight
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return  BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize google maps
        checkMyPermission()
        if (isPermissionGranted) {
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment?.getMapAsync(this)
            myLocationButton = mapFragment?.view?.findViewById(0x2)

            //Remove the default my location button
            if (myLocationButton != null) {
                myLocationButton?.visibility = View.GONE
            }
        }

        // Configure bottom sheet
        val bottomSheet = view.findViewById<ConstraintLayout>(R.id.bottomSheet)
        BottomSheetBehavior.from(bottomSheet).apply {
            peekHeight = 210
            this.state = BottomSheetBehavior.STATE_EXPANDED
            //ToDo : set bottom sheet
        }

        //Bottom sheet will collapsed when search edit text is clicked
        val etSearch = view.findViewById<EditText>(R.id.etSearch)
        etSearch.setOnClickListener {
            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_COLLAPSED
        }

        // Configure myLocation Button
        view.findViewById<FloatingActionButton>(R.id.fabMyLocation).setOnClickListener {
            if (myLocationButton != null)
                myLocationButton?.callOnClick()
        }

        // Configure Restaurant Recycler View
        val restaurantDataList = ArrayList<RestaurantData>()
        restaurantDataList.addAll(listRestaurantData)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_restaurant)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = true
        recyclerView.adapter = RestaurantAdapter(requireContext(), restaurantDataList) {
            val intent = Intent(context, RestaurantDetailsActivity::class.java)
            intent.putExtra("OBJECT_INTENT", it)
            startActivity(intent)
        }
    }

    private val listRestaurantData : ArrayList<RestaurantData>
        get() {
            val dataName = resources.getStringArray(R.array.data_restaurant_name)
            val dataHighlight = resources.getStringArray(R.array.data_restaurant_highlight)
            val dataPhoto = resources.obtainTypedArray(R.array.data_restaurant_photo)
            val dataDesc = resources.getStringArray(R.array.data_restaurant_description)

            val lists = ArrayList<RestaurantData>()
            for (i in 1..5){
                for (i in dataName.indices){
                    val restaurantData = RestaurantData(
                        dataName[i], dataHighlight[i], dataPhoto.getResourceId(i, -1), dataDesc[i], 0.0, 0.0
                    )
                    lists.add(restaurantData)
                }
            }
            return lists
        }

    private fun checkMyPermission() {
        Dexter.withContext(this@DiscoverFragment.activity)
            .withPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    isPermissionGranted = true
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri: Uri = Uri.fromParts("package", context!!.packageName, "")
                    intent.data = uri

                    Toast.makeText(this@DiscoverFragment.activity, "Turn on your location permission", Toast.LENGTH_LONG).show()
                    startActivity(intent)
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                   token?.continuePermissionRequest()
                }
            }).check()
    }


    override fun onMarkerClick(p0: Marker): Boolean {
        return true
    }

    override fun onInfoWindowClick(p0: Marker) {
        val dataName = resources.getStringArray(R.array.data_restaurant_name)
        val dataHighlight = resources.getStringArray(R.array.data_restaurant_highlight)
        val dataPhoto = resources.obtainTypedArray(R.array.data_restaurant_photo)
        val dataDesc = resources.getStringArray(R.array.data_menu_description)

        val intent = Intent(context, RestaurantDetailsActivity::class.java)
        intent.putExtra("OBJECT_INTENT", RestaurantData(
            dataName[0], dataHighlight[0], dataPhoto.getResourceId(0, -1), dataDesc[0], 0.0, 0.0
        ))
        startActivity(intent)
    }
}