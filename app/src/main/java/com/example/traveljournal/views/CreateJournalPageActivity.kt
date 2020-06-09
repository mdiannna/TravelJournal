package com.example.traveljournal.views

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.traveljournal.R
import com.example.traveljournal.data.APIServiceImpl
import com.example.traveljournal.data.models.OpenTripApiObject
import com.example.traveljournal.data.models.OpenTripDetailedObject
import com.example.traveljournal.data.remote.APIService
//import com.example.traveljournal.data.remote.ApiHelper.apiService
import com.example.traveljournal.viewmodels.CreateJournalPageViewModel
import com.example.traveljournal.viewmodels.LocationViewModel
import kotlinx.android.synthetic.main.activity_create_journal_page.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateJournalPageActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var createJournalPageViewModel: CreateJournalPageViewModel

    private val LOCATION_DELTA = 0.01
    val PERMISSION_ID = 42
    private var obtainedLatitude: Double? = 0.0
    private var obtainedLongitude: Double? = 0.0

    private val apiService: APIService = APIServiceImpl()
    private lateinit var locationViewModel: LocationViewModel

    override fun onStart() {
        super.onStart()
        invokeLocationAction()
    }

    private fun invokeLocationAction() {
        if (this.checkPermissions()) {
            if (this.isLocationEnabled()) {
                startLocationUpdate()
//                startPlaceDescriptionUpdate()
            }
        } else {
            requestPermissions()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_journal_page)

        var buttonUploadPhoto = Button(this)
        buttonUploadPhoto.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            text = "+ Photo"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnUploadPhoto
        }

        var buttonNextPage = Button(this)
        buttonNextPage.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            text = "Next"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnNextPage
        }

        var buttonSaveJournal = Button(this)
        buttonSaveJournal.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            text = "Save journal"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnSaveJournal
        }

        var buttonUpdateLocation = findViewById<Button>(R.id.buttonUpdateLocation)

        buttonUpdateLocation.setOnClickListener(this)
        buttonUploadPhoto.setOnClickListener(this)
        buttonNextPage.setOnClickListener(this)
        buttonSaveJournal.setOnClickListener(this)

        createJournalLayout.addView(buttonNextPage)
        createJournalLayout.addView(buttonSaveJournal)
        createJournalLayout.addView(buttonUploadPhoto)

        locationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
        createJournalPageViewModel = ViewModelProviders.of(this).get(CreateJournalPageViewModel::class.java)
    }

    private fun startLocationUpdate() {
        locationViewModel.getLocationData().observe(this, Observer {

            this.obtainedLatitude = it.latitude
            this.obtainedLongitude = it.longitude
            findViewById<TextView>(R.id.latTextView).text = this.obtainedLatitude.toString()
            findViewById<TextView>(R.id.lonTextView).text = this.obtainedLongitude.toString()

            updateDescriptionByLocation(it.latitude, it.longitude)
        })
    }

    // Not working with the ViewModel Observer and retrofit and coroutines
    private fun startPlaceDescriptionUpdate(lat: Double?, lng: Double?) {
        createJournalPageViewModel!!.response.observe(this, androidx.lifecycle.Observer {
            this.descriptionTextView.text =
                it.properties.name + "(" + it.properties.kinds + ", " + it.properties.rate + ")"
            Toast.makeText(this, it.properties.name, Toast.LENGTH_LONG).show()

        })
        createJournalPageViewModel!!.errorMessage.observe(this, androidx.lifecycle.Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnUploadPhoto -> {

            }
            R.id.btnNextPage -> {
                val intent = Intent(this, CreateJournalPageActivity::class.java)
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
        var locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    //
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    private fun setLatitude(latitude: Double) {
        this.obtainedLatitude = latitude
    }

    private fun setLongitude(longitude: Double) {
        this.obtainedLongitude = longitude
    }

    private fun setCoordinates(latitude: Double, longitude: Double) {
        this.obtainedLatitude = latitude
        this.obtainedLongitude = longitude
    }

    private fun getLatitude(): Double? {
        return this.obtainedLatitude
    }

    private fun getLongitude(): Double? {
        return this.obtainedLongitude
    }

    private fun getPlacesDetailedInfo(id: String) {
        GlobalScope.launch {
            kotlin.runCatching {
                apiService.getPlacesDetailedInfo(id)
            }.onSuccess {
                onPlacesDetailsFetched(it)
            }.onFailure {
                onPlacesDetailsFetchedError(it)
            }
        }
    }


    private fun onPlacesDetailsFetched(details: OpenTripDetailedObject) {
        println("DETAILS!!!")
        println(details)
        println(details.name)
        println(details.wikipediaExtracts.text)
        println(details.image)

        //  https://www.tutorialkart.com/kotlin-android/original-thread-created-view-hierarchy-can-touch-views/
        this@CreateJournalPageActivity.runOnUiThread(java.lang.Runnable {
            findViewById<TextView>(R.id.descriptionTextView).text = details.wikipediaExtracts.text
            //   TODO: set image from url (maybe using picasso)
            findViewById<ImageView>(R.id.placeImage).setImageResource(
                R.drawable.louvre
            )
        })
    }

    private fun onPlacesDetailsFetchedError(error: Throwable) {
        println("ERROR!!!")
        print(error)

        this@CreateJournalPageActivity.runOnUiThread(java.lang.Runnable {
            findViewById<TextView>(R.id.descriptionTextView).text = "Error:" + error
        })
    }

    private fun getPlacesByCoordinates(
        lngMin: Double,
        latMin: Double,
        lngMax: Double,
        latMax: Double,
        kinds: String
    ): String {
        //TODO: Activity is not the best place for calling API request, even it is possible to perform
        // such operation in activity, it is recomended to extract your business logic into separate component
        var result: String = ""

        GlobalScope.launch {
            kotlin.runCatching {
                apiService.getPlacesByCoordinates(lngMin, latMin, lngMax, latMax, kinds)
            }.onSuccess {
                result = onPlacesFetched(it)
                print(it)
            }.onFailure {
                result = onPlacesFetchedError(it)
            }
        }

        return result
    }

    private fun onPlacesFetched(places: OpenTripApiObject): String {
        println("PLACES!!!")
        println("PLACES!!!")
        println("PLACES!!!")
        println(places)
        println(places.features)

        this@CreateJournalPageActivity.runOnUiThread(java.lang.Runnable {
            findViewById<TextView>(R.id.nameTextView).text =
                places.features[0].properties.name + " (" + places.features[0].properties.kinds + ")"
            // TODO: get description of place with correct id
            getPlacesDetailedInfo(places.features[0].properties.id)
            //  getPlacesDetailedInfo(id)
        })

        return places.toString()
    }

    private fun onPlacesFetchedError(error: Throwable): String {
        println("ERROR!!!")
        println("ERROR!!!")
        println("ERROR!!!")
        print(error)
        this@CreateJournalPageActivity.runOnUiThread(java.lang.Runnable {
            findViewById<TextView>(R.id.nameTextView).text = "Error:" + error
        })

        return "Error:" + error
    }

    private fun updateDescriptionByLocation(lat: Double?, lng: Double?) {
        //TODO: Call fetching of data from API
        // getPlacesByCoordinates() Add all required parameters

        var <TextView> descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        var <TextView> nameTextView = findViewById<TextView>(R.id.nameTextView)

        nameTextView.text = "Getting places"
        descriptionTextView.text = "Getting places"

        if (lat == null || lng == null || lat < 0.001 || lng < 0.001) {
            descriptionTextView.text = "No latitude or longitude."
            nameTextView.text = "No latitude or longitude."
        } else {
            println("Lat:");
            print(lat);
            println("Lng:");
            print(lng);
            println("Max Lat:");
            print(lat + LOCATION_DELTA);

            getPlacesByCoordinates(
                lng - LOCATION_DELTA, lat - LOCATION_DELTA,
                lng + LOCATION_DELTA, lat + LOCATION_DELTA,
                "interesting_places"
            )
        }
    }
}

