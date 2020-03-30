package com.example.traveljournal

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.provider.Settings
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_create_journal.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory

class CreateJournalActivity : AppCompatActivity(), View.OnClickListener  {
    val PERMISSION_ID = 42
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var obtainedLatitude: Double? = 0.0
    private var obtainedLongitude: Double? = 0.0
    val BASE_URL = "http://api.opentripmap.com/0.1/ru/"
//    http://api.opentripmap.com/0.1/ru/places/bbox?lon_min=28.76&lat_min=47.05&lon_max=28.9&lat_max=47.07&kinds=interesting_places&format=geojson&apikey=5ae2e3f221c38a28845f05b6eab28f2de056a215a99556e91c9be261


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_journal)

        var buttonUploadPhoto = Button(this)
        buttonUploadPhoto.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Upload photo"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnUploadPhoto
        }

        var buttonNextPage = Button(this)
        buttonNextPage.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Next"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnNextPage
        }

        var buttonSaveJournal = Button(this)
        buttonSaveJournal.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Save journal"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnSaveJournal
        }

        var buttonUpdateLocation = findViewById<Button>(R.id.buttonUpdateLocation)

        buttonUploadPhoto.setOnClickListener(this)
        buttonNextPage.setOnClickListener(this)
        buttonSaveJournal.setOnClickListener(this)

        createJournalLayout.addView(buttonNextPage)
        createJournalLayout.addView(buttonSaveJournal)
        createJournalLayout.addView(buttonUploadPhoto)

        // Get Location - To be tested
        // https://www.androdocs.com/kotlin/getting-current-location-latitude-longitude-in-android-using-kotlin.html
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()
    }

    override fun onClick(v:View?) {
        when(v?.id) {
            R.id.btnUploadPhoto -> {

            }
            R.id.btnNextPage-> {
                val intent = Intent(this, CreateJournalActivity::class.java).apply {
                    // putExtra(EXTRA_MESSAGE, "HELLO")
                }
                startActivity(intent)
            }
            R.id.btnSaveJournal -> {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE, "Journal saved")
                }
                startActivity(intent)
            }
            R.id.buttonUpdateLocation -> {
                getLastLocation()
            }
            else -> {

            }
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
            )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Granted. Start getting the location information
            }
        }
    }


    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        var latitude = location.latitude
                        var longitude = location.longitude
                        findViewById<TextView>(R.id.latTextView).text = latitude.toString()
                        findViewById<TextView>(R.id.lonTextView).text = longitude.toString()
                        // TODO: updateDescriptionByLocation, care are api call la get description by x, y etc
                        this.setCoordinates(latitude, longitude)
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            findViewById<TextView>(R.id.latTextView).text = mLastLocation.latitude.toString()
            findViewById<TextView>(R.id.lonTextView).text = mLastLocation.longitude.toString()
        }
    }

    private fun setLatitude(latitude:Double) {
        this.obtainedLatitude = latitude
    }

    private fun setLongitude(longitude:Double) {
        this.obtainedLongitude = longitude
    }

    private fun setCoordinates(latitude:Double, longitude:Double) {
        this.obtainedLatitude = latitude
        this.obtainedLongitude = longitude
    }

    private fun getLatitude(): Double? {
        return this.obtainedLatitude
    }

    private fun getLongitude(): Double? {
        return this.obtainedLongitude
    }

    // API requests
    //    http://api.opentripmap.com/0.1/ru/places/bbox?lon_min=28.76&lat_min=47.05&lon_max=28.9&lat_max=47.07&kinds=interesting_places&format=geojson&apikey=5ae2e3f221c38a28845f05b6eab28f2de056a215a99556e91c9be261
    private val customerGatewayServiceAPI = createApiService(
        BASE_URL,
        OpenTripApiInterface::class.java
    )

    private fun <T> createApiService(baseURL: String, service: Class <T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(service)
    }


}
