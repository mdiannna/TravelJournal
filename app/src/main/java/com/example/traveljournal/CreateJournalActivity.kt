package com.example.traveljournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_create_journal.*
import kotlinx.android.synthetic.main.activity_main.*

class CreateJournalActivity : AppCompatActivity(), View.OnClickListener  {

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

        buttonUploadPhoto.setOnClickListener(this)
        buttonNextPage.setOnClickListener(this)
        buttonSaveJournal.setOnClickListener(this)

        createJournalLayout.addView(buttonNextPage)
        createJournalLayout.addView(buttonSaveJournal)
        createJournalLayout.addView(buttonUploadPhoto)
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
            else -> {

            }
        }
    }
}
