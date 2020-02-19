package com.example.traveljournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Button
import android.widget.LinearLayout
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val textView = findViewById<TextView>(R.id.mainTextView).apply {
            text = message
        }


        // Create buttons
        var buttonCreateJournal = Button(this)
        buttonCreateJournal.apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Create journal"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnCreateJournal
        }

        // TODO: photo
        var buttonTopLocations = Button(this)
        buttonTopLocations.apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Locat."
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnTopLocations
        }

        // TODO: photo
        var buttonMap = Button(this)
        buttonMap.apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Map"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnMap
        }

        // TODO: photo
        var buttonHelp = Button(this)
        buttonHelp.apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Help"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnHelp
        }


        buttonCreateJournal.setOnClickListener(this)
        buttonMap.setOnClickListener(this)
        buttonTopLocations.setOnClickListener(this)
        buttonHelp.setOnClickListener(this)

        linearLayout.addView(buttonCreateJournal)

        linearLayout.addView(buttonTopLocations)
        linearLayout.addView(buttonMap)
        linearLayout.addView(buttonHelp)

    }

    override fun onClick(v:View?) {
        when(v?.id) {
            R.id.btnCreateJournal -> {
                val intent = Intent(this, CreateJournalActivity::class.java).apply {
                // putExtra(EXTRA_MESSAGE, "HELLO")
                }
                startActivity(intent)
            }
            R.id.btnMap -> {
                val intentMap = Intent(this, MapActivity::class.java).apply {
                    // putExtra(EXTRA_MESSAGE, "HELLO")
                }
                startActivity(intentMap)
            }
            else -> {

            }
        }
    }
}
