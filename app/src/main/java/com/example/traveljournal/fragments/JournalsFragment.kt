package com.example.traveljournal.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.traveljournal.*

import kotlinx.android.synthetic.main.activity_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JournalsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JournalsFragment : Fragment(), View.OnClickListener  {
    // TODO: Rename and change types of parameters
    private var message: String? = null

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

    //        val textView = findViewById<TextView>(R.id.mainTextView).apply {
    //            text = message
    //        }

        // Create buttons
        var buttonCreateJournal = Button(getActivity())
        buttonCreateJournal.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            text = "Create journal"
            setAllCaps(false)
            textSize = 20f
            id = R.id.btnCreateJournal
        }

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
//
//        // TODO: photo
//        var buttonMap = Button(getActivity())
//        buttonMap.apply {
//            layoutParams = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT)
//            text = "Map"
//            setAllCaps(false)
//            textSize = 20f
//            id = R.id.btnMap
//        }
//
//        // TODO: photo
//        var buttonHelp = Button(this)
//        buttonHelp.apply {
//            layoutParams = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT)
//            text = "About"
//            setAllCaps(false)
//            textSize = 20f
//            id = R.id.btnHelp
//        }
//

        buttonCreateJournal.setOnClickListener(this)
        buttonJournal1.setOnClickListener(this)
//        buttonMap.setOnClickListener(this)
//        buttonTopLocations.setOnClickListener(this)
//        buttonHelp.setOnClickListener(this)

//        mainButtonsLayout.addView(buttonTopLocations)
//        mainButtonsLayout.addView(buttonMap)
//        mainButtonsLayout.addView(buttonHelp)


        linearLayout.addView(buttonCreateJournal)
        // in the future will be more buttons with journals
        linearLayout.addView(buttonJournal1)


        // Inflate the layout for this fragment
        return rootView
    }


    override fun onClick(v:View?) {
        when(v?.id) {
            R.id.btnCreateJournal -> {
                val intent = Intent(getActivity(), CreateJournalActivity::class.java).apply {
                    // putExtra(EXTRA_MESSAGE, "HELLO"
                }
                startActivity(intent)
            }
            R.id.btnJournal1 -> {
                val intent = Intent(getActivity(), JournalActivity::class.java).apply {
                    // putExtra(EXTRA_MESSAGE, "HELLO")
                }
                startActivity(intent)
            }
            else -> {

            }
        }
    }
}
