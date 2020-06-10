package com.example.traveljournal.views.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.get
import com.example.traveljournal.*
import com.example.traveljournal.adapters.JournalsGridAdapter
import com.example.traveljournal.viewmodels.JournalViewModel
import com.example.traveljournal.views.CreateJournalActivity
import com.example.traveljournal.views.CreateJournalPageActivity
import com.example.traveljournal.views.JournalActivity

class JournalsFragment : Fragment(), View.OnClickListener  {
    // TODO: Rename and change types of parameters
    private var message: String? = null
    private lateinit var journalViewModel: JournalViewModel

    private val itemList:Array<String>
        get() = arrayOf("Louvre", "Stefan cel Mare", "Arcul de Triumf")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            message = it.getString(EXTRA_MESSAGE).toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_journals, container, false)

        var linearLayout:LinearLayout =  rootView.findViewById(R.id.journalLayout)

        // Create and populate GridView
        val gridview = rootView.findViewById<GridView>(R.id.gridview)
        val adapter = getActivity()?.let {
            JournalsGridAdapter(
                it,
                R.layout.journal_item
//                journalViewModel.allJournals()
            )
        }
        gridview.adapter = adapter

//        journalViewModel = ViewModelProviders.of(this).get(JournalViewModel::class.java)

        journalViewModel = ViewModelProvider(this).get(JournalViewModel::class.java)

        journalViewModel.allJournals.observe(viewLifecycleOwner, Observer {
                journals -> journals?.let {
                if (adapter != null) {
                    adapter.setJournals(it)
                }
            }
        })

        // Create buttons
        var buttonCreateJournal = rootView.findViewById<Button>(R.id.btnCreateJournal)

        var buttonJournal1 = Button(activity)
        buttonJournal1.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Louvre"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnJournal1
        }

        // TODO: photo
        var buttonTopLocations = Button(activity)
        buttonTopLocations.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Locat."
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnTopLocations
        }

        buttonCreateJournal.setOnClickListener(this)
        buttonJournal1.setOnClickListener(this)

        gridview.onItemClickListener = AdapterView.OnItemClickListener {
                parent, v, position, id
                        -> startJournalActivity(position, id)
        }


        // Inflate the layout for this fragment
        return rootView
    }

//    TODO: customize
    private fun startJournalActivity(position:Int, id:Long) {
        val intent = Intent(getActivity(), JournalActivity::class.java)
        startActivity(intent)
    }


    override fun onClick(v:View?) {
        when(v?.id) {
            R.id.btnCreateJournal -> {
                val intent = Intent(getActivity(), CreateJournalActivity::class.java)
                startActivity(intent)
            }
            R.id.btnJournal1 -> {
                val intent = Intent(getActivity(), JournalActivity::class.java)
                startActivity(intent)
            }
            else -> {

            }
        }
    }
}
