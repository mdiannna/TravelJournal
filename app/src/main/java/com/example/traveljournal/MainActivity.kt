package com.example.traveljournal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonCreateJournal = Button(this)
        buttonCreateJournal.apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Create journal"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnCreateJournal
        }

        buttonCreateJournal.setOnClickListener(this)
        linearLayout.addView(buttonCreateJournal)
    }

    override fun onClick(v:View?) {
        when(v?.id) {
            R.id.btnCreateJournal -> {

            }
            else -> {

            }
        }
    }
}
