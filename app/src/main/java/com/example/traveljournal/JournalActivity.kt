package com.example.traveljournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.traveljournal.R.color.colorPrimaryLight
import kotlinx.android.synthetic.main.activity_journal.*
import kotlinx.android.synthetic.main.activity_main.*

class JournalActivity : AppCompatActivity(), View.OnClickListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal)

        val textView = findViewById<TextView>(R.id.louvreText).apply {
            text = "Louvre is the world's largest art museum and a historic monument" +
                    " in Paris, France. A central landmark of the city, it is located" +
                    " on the Right Bank of thr Siene in the city's 1st arrondissement(district or" +
                    " ward). Approximately 38000 objects from prehistory to the 21st century are " +
                    "exhibited over am area of 72735 square meters. In 2019, the Louvre received" +
                    " 9.6 million visitors."
        }

        // TODO: photo
//        var buttonPrintJournal = Button(this)
//        buttonPrintJournal.apply {
//            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT)
//            text = "Print journal"
//            setAllCaps(false)
//            textSize = 20f
//            id = R.id.btnPrintJournal
//        }
//        buttonPrintJournal.setBackgroundColor(colorPrimaryLight)
//
//        buttonPrintJournal.setOnClickListener(this)
//        journalLayout.addView(buttonPrintJournal)

    }

    override fun onClick(v:View?) {
        when(v?.id) {
            R.id.btnPrintJournal -> {
                // TODO
            }
            else -> {
            }
        }
    }

}
