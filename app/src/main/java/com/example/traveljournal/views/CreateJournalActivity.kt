package com.example.traveljournal.views

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
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.traveljournal.R
import com.example.traveljournal.data.APIService
import com.example.traveljournal.data.APIServiceImpl
import com.example.traveljournal.data.models.OpenTripApiObject
import com.example.traveljournal.data.models.OpenTripDetailedObject
import com.example.traveljournal.viewmodels.CreateJournalViewModel
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_create_journal.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateJournalActivity : AppCompatActivity(), View.OnClickListener  {
    val PERMISSION_ID = 42
//    lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var obtainedLatitude: Double? = 0.0
    private var obtainedLongitude: Double? = 0.0

    private val apiService: APIService = APIServiceImpl()
    private lateinit var viewModel: CreateJournalViewModel

    override fun onStart() {
        super.onStart()
        invokeLocationAction()
    }

    private fun invokeLocationAction() {
        when {
            isLocationEnabled() -> startLocationUpdate()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_journal)

        var buttonUploadPhoto = Button(this)
        buttonUploadPhoto.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "+ Photo"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnUploadPhoto
        }

        var buttonNextPage = Button(this)
        buttonNextPage.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Next"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnNextPage
        }

        var buttonSaveJournal = Button(this)
        buttonSaveJournal.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
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

        //TODO
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        //        // getLastLocation()

        viewModel = ViewModelProviders.of(this).get(CreateJournalViewModel::class.java)

        updateDescriptionByLocation(obtainedLatitude, obtainedLongitude)
    }

    private fun startLocationUpdate() {
        viewModel.getLocationData().observe(this, Observer {
            findViewById<TextView>(R.id.latTextView).text = it.latitude.toString()
            findViewById<TextView>(R.id.lonTextView).text = it.longitude.toString()
        })
    }

    override fun onClick(v:View?) {
        when(v?.id) {
            R.id.btnUploadPhoto -> {

            }
            R.id.btnNextPage -> {
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
                invokeLocationAction()
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
//
//    private fun checkPermissions(): Boolean {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
//            return true
//        }
//        return false
//    }
//
//    private fun requestPermissions() {
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
//            PERMISSION_ID
//        )
//    }
//
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        if (requestCode == PERMISSION_ID) {
//            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                // Granted. Start getting the location information
//            }
//        }
//    }

//
//    @SuppressLint("MissingPermission")
//    private fun getLastLocation() {
//        if (checkPermissions()) {
//            if (isLocationEnabled()) {
//
//                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
//                    var location: Location? = task.result
//                    if (location == null) {
//                        requestNewLocationData()
//                    } else {
//                        var latitude = location.latitude
//                        var longitude = location.longitude
//                        findViewById<TextView>(R.id.latTextView).text = latitude.toString()
//                        findViewById<TextView>(R.id.lonTextView).text = longitude.toString()
//                        // TODO: updateDescriptionByLocation, care are api call la get description by x, y etc
//                        this.setCoordinates(latitude, longitude)
//                        updateDescriptionByLocation(latitude, longitude)
//                    }
//                }
//            } else {
//                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
//                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                startActivity(intent)
//            }
//        } else {
//            requestPermissions()
//        }
//    }
//
//    @SuppressLint("MissingPermission")
//    private fun requestNewLocationData() {
//        var mLocationRequest = LocationRequest()
//        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        mLocationRequest.interval = 0
//        mLocationRequest.fastestInterval = 0
//        mLocationRequest.numUpdates = 1
//
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        mFusedLocationClient!!.requestLocationUpdates(
//            mLocationRequest, mLocationCallback,
//            Looper.myLooper()
//        )
//    }
//
//    private val mLocationCallback = object : LocationCallback() {
//        override fun onLocationResult(locationResult: LocationResult) {
//            var mLastLocation: Location = locationResult.lastLocation
//            findViewById<TextView>(R.id.latTextView).text = mLastLocation.latitude.toString()
//            findViewById<TextView>(R.id.lonTextView).text = mLastLocation.longitude.toString()
//        }
//    }
//
//    private fun setLatitude(latitude:Double) {
//        this.obtainedLatitude = latitude
//    }
//
//    private fun setLongitude(longitude:Double) {
//        this.obtainedLongitude = longitude
//    }
//
//    private fun setCoordinates(latitude:Double, longitude:Double) {
//        this.obtainedLatitude = latitude
//        this.obtainedLongitude = longitude
//    }
//
//    private fun getLatitude(): Double? {
//        return this.obtainedLatitude
//    }
//
//    private fun getLongitude(): Double? {
//        return this.obtainedLongitude
//    }

    private fun getPlacesDetailedInfo(id: String) {
        GlobalScope.launch{
            kotlin.runCatching {
                apiService.getPlacesDetailedInfo(id)
            }.onSuccess{
                onPlacesDetailsFetched(it)
            }.onFailure{
                onPlacesDetailsFetchedError(it)
            }
        }
    }


    private fun onPlacesDetailsFetched(details: OpenTripDetailedObject) {
//      For easier debug:
        println("DETAILS!!!")
        println(details)
        println(details.name)
        println(details.wikipediaExtracts.text)
        println(details.image)

//        https://www.tutorialkart.com/kotlin-android/original-thread-created-view-hierarchy-can-touch-views/
        this@CreateJournalActivity.runOnUiThread(java.lang.Runnable {
            findViewById<TextView>(R.id.descriptionTextView).text = details.wikipediaExtracts.text
//            TODO: set image from url (maybe using picasso)
            findViewById<ImageView>(R.id.placeImage).setImageResource(
                R.drawable.louvre
            )
//            findViewById<ImageView>(R.id.placeImage).setImageURI(details.image)

        })
    }

    private fun onPlacesDetailsFetchedError(error: Throwable) {
//      For easier debug:
        println("ERROR!!!")
        print(error)
        this@CreateJournalActivity.runOnUiThread(java.lang.Runnable {
            findViewById<TextView>(R.id.descriptionTextView).text = "Error:" + error
        })
    }


    private fun getPlacesByCoordinates(lngMin: Double,
                                       latMin: Double,
                                       lngMax:Double,
                                       latMax: Double,
                                       kinds:String) :String {
        //TODO: Activity is not the best place for calling API request, even it is possible to perform
        // such operation in activity, it is recomended to extract your business logic into separate component
        var result:String = ""

        GlobalScope.launch{
            kotlin.runCatching {
                apiService.getPlacesByCoordinates(lngMin, latMin, lngMax, latMax,kinds)
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
//      For easier debug:
        println("PLACES!!!")
        println("PLACES!!!")
        println("PLACES!!!")
        println(places)
        println(places.features)

//        https://www.tutorialkart.com/kotlin-android/original-thread-created-view-hierarchy-can-touch-views/
        this@CreateJournalActivity.runOnUiThread(java.lang.Runnable {
            findViewById<TextView>(R.id.nameTextView).text =
                places.features[0].properties.name + " (" +places.features[0].properties.kinds + ")"
            // TODO: get description of place with correct id
             getPlacesDetailedInfo(places.features[0].properties.id)
//            getPlacesDetailedInfo(id)
        })

        return places.toString()
//
//        TODO: another call to api with id to get description
    }

    private fun onPlacesFetchedError(error: Throwable):String {
//      For easier debug:
        println("ERROR!!!")
        println("ERROR!!!")
        println("ERROR!!!")
        print(error)
        this@CreateJournalActivity.runOnUiThread(java.lang.Runnable {
            findViewById<TextView>(R.id.nameTextView).text = "Error:" + error
        })

        return "Error:" + error
    }

    private val LOCATION_DELTA = 0.01

    private fun updateDescriptionByLocation(lat:Double?, lng:Double?) {
        //TODO: Call fetching of data from API
        // getPlacesByCoordinates() Add all required parameters

        var <TextView> descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        var <TextView> nameTextView = findViewById<TextView>(R.id.nameTextView)

        nameTextView.text = "Getting places"
        descriptionTextView.text = "Getting places"

        if(lat==null || lng==null || lat < 0.001  || lng < 0.001)  {
            descriptionTextView.text = "No latitude or longitude."
            nameTextView.text = "No latitude or longitude."
        } else {
            println("Lat:");
            print(lat);
            println("Lng:");
            print(lng);
            println("Max Lat:");
            print(lat+LOCATION_DELTA);

             getPlacesByCoordinates( lng-LOCATION_DELTA,lat-LOCATION_DELTA,
             lng+LOCATION_DELTA, lat+LOCATION_DELTA,
             "interesting_places")
        }
    }
}
