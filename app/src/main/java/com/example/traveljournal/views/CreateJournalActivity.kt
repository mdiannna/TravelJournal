package com.example.traveljournal.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.traveljournal.R
import com.example.traveljournal.data.database.Journal
import com.example.traveljournal.viewmodels.CreateJournalViewModel
import org.jetbrains.anko.adapterViewFlipper

class CreateJournalActivity : AppCompatActivity(), View.OnClickListener  {

    private lateinit var editJournalView:EditText
    private lateinit var  createJournalViewModel: CreateJournalViewModel

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_journal)

        createJournalViewModel = ViewModelProvider(this).get(CreateJournalViewModel::class.java)

//        createJournalViewModel.allJournals.observe(this, Observer {
//            journals -> journals?.let {adapter.setJournals(it)}
//        })
//
        editJournalView = findViewById(R.id.editJournalName)

//        TODO: viewModel


        var btnNext = findViewById<Button>(R.id.JournalNext)
        btnNext.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.JournalNext -> {
                val newIntent = Intent()
                if(TextUtils.isEmpty(editJournalView.text)) {
                    setResult(Activity.RESULT_CANCELED, newIntent)
                } else {
                    val journalName = editJournalView.text.toString()
                    val journal = Journal(journalName)

                    createJournalViewModel.insert(journal)
                    newIntent.putExtra(EXTRA_REPLY, journalName)
                    setResult(Activity.RESULT_OK, newIntent)
                }

                finish()

                // TODO: save Data to db using viewModel
              //  val intent = Intent(this, CreateJournalPageActivity::class.java)
               // startActivity(intent)
            }
            else -> {

            }
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.journallistsql.REPLY"
    }
}