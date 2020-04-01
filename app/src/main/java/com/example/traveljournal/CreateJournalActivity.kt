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
import com.example.traveljournal.data.APIService
import com.example.traveljournal.data.APIServiceImpl
import com.example.traveljournal.data.models.OpenTripApiObject
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

    private val apiService: APIService = APIServiceImpl()

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

        updateDescriptionByLocation(obtainedLatitude, obtainedLongitude)
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
                        updateDescriptionByLocation(latitude, longitude)
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

    private fun getPlacesByCoordinates(latMin: Double,
                                       lngMin: Double,
                                       latMax: Double,
                                       lngMax:Double,
                                       kinds:String) :String {
        //TODO: Activity is not the best place for calling API request, even it is possible to perform
        // such operation in activity, it is recomended to extract your business logic into separate component
        var result:String = ""

        GlobalScope.launch{
            kotlin.runCatching {
                apiService.getPlacesByCoordinates(latMin, lngMin, latMax, lngMax, kinds)
            }.onSuccess{
               result = onPlacesFetched(it)
               print(it)
            }.onFailure{
                result = onPlacesFetchedError(it)
            }
        }

        return result
    }

    private fun onPlacesFetched(places: OpenTripApiObject):String {
        println("PLACES!!!")
        println("PLACES!!!")
        println("PLACES!!!")
        println(places)
        println(places.features)
//        print(places.features.properties)

//        https://www.tutorialkart.com/kotlin-android/original-thread-created-view-hierarchy-can-touch-views/
        this@CreateJournalActivity.runOnUiThread(java.lang.Runnable {
            findViewById<TextView>(R.id.descriptionTextView).text = "Fetch places" + places
        })

        return places.toString()
//
//        TODO: another call to api with id to get description
    }

    private fun onPlacesFetchedError(error: Throwable):String {
        println("ERROR!!!")
        println("ERROR!!!")
        println("ERROR!!!")
        print(error)
        return "Error:" + error
    }

    private val LOCATION_DELTA = 0.01

    private fun updateDescriptionByLocation(lat:Double?, lng:Double?) {
        //TODO: Call fetching of data from API
        // getPlacesByCoordinates() Add all required parameters

        var <TextView> descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)

        findViewById<TextView>(R.id.descriptionTextView).text = "Getting plalces"

        if(lat==null || lng==null || lat < 0.001  || lng < 0.001)  {
            findViewById<TextView>(R.id.descriptionTextView).text = "No latitude or longitude."
        } else {
            println("Lat:");
            print(lat);
            println("Lng:");
            print(lng);
            println("Max Lat:");
            print(lat+LOCATION_DELTA);


            var result = getPlacesByCoordinates(lat-LOCATION_DELTA, lng-LOCATION_DELTA,
            lat+LOCATION_DELTA, lng+LOCATION_DELTA,
             "interesting_places")
            descriptionTextView.text = "!" + result

        }
    }
}
