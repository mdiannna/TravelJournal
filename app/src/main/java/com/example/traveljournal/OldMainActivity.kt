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

class OldMainActivity : AppCompatActivity(), View.OnClickListener {

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

        var buttonJournal1 = Button(this)
        buttonJournal1.apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Louvre"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnJournal1
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
            text = "About"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnHelp
        }


        buttonCreateJournal.setOnClickListener(this)
        buttonJournal1.setOnClickListener(this)
        buttonMap.setOnClickListener(this)
        buttonTopLocations.setOnClickListener(this)
        buttonHelp.setOnClickListener(this)

        mainButtonsLayout.addView(buttonTopLocations)
        mainButtonsLayout.addView(buttonMap)
        mainButtonsLayout.addView(buttonHelp)


        linearLayout.addView(buttonCreateJournal)
        // in the future will be more buttons with journals
        linearLayout.addView(buttonJournal1)

    }

    override fun onClick(v:View?) {
        when(v?.id) {
            R.id.btnCreateJournal -> {
                val intent = Intent(this, CreateJournalActivity::class.java).apply {
                    // putExtra(EXTRA_MESSAGE, "HELLO"
                }
                startActivity(intent)
            }
            R.id.btnJournal1 -> {
                val intent = Intent(this, JournalActivity::class.java).apply {
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
            R.id.btnHelp -> {
                val intentMap = Intent(this, AboutActivity::class.java).apply {
                    // putExtra(EXTRA_MESSAGE, "HELLO")
                }
                startActivity(intentMap)
            }
            R.id.btnTopLocations -> {
                val intentMap = Intent(this, TopLocationsActivity::class.java).apply {
                    // putExtra(EXTRA_MESSAGE, "HELLO")
                }
                startActivity(intentMap)
            }
            else -> {

            }
        }
    }
}
