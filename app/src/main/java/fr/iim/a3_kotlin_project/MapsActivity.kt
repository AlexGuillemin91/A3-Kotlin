package fr.iim.a3_kotlin_project

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import fr.iim.a3_kotlin_project.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var gMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap

        val city: String? = intent.getStringExtra(EXTRA_LOCATION)
        val geocoder = Geocoder(this)
        val location = geocoder.getFromLocationName(city, 1)
        val lat:Double = location[0].latitude
        val long:Double = location[0].longitude


        // Add a marker in Sydney and move the camera
        val point = LatLng(lat, long)
        val marker = gMap.addMarker(MarkerOptions().position(point).title(city))
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
        gMap.moveCamera(CameraUpdateFactory.newLatLng(point))
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15f))
    }

    companion object {
        const val EXTRA_LOCATION = "EXTRA_LOCATION"
    }
}