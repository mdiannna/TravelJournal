package com.example.traveljournal.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.traveljournal.R

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
