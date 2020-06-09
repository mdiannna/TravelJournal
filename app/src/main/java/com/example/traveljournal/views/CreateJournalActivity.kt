package com.example.traveljournal.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import com.example.traveljournal.R

class CreateJournalActivity : AppCompatActivity(), View.OnClickListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_journal)
//        TODO: viewModel

        var btnNext = findViewById<Button>(R.id.JournalNext)
        btnNext.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.JournalNext -> {
                // TODO: save Data to db using viewModel
                val intent = Intent(this, CreateJournalPageActivity::class.java)
                startActivity(intent)
            }
            else -> {

            }
        }
    }
}