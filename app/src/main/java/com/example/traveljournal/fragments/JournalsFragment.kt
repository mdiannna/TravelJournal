package com.example.traveljournal.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
        val adapter = getActivity()?.let { JournalsGridAdapter(it, R.layout.journal_item, itemList) }
        gridview.adapter = adapter



        // Create buttons
//        var buttonCreateJournal = Button(getActivity())

        var buttonCreateJournal = rootView.findViewById<Button>(R.id.btnCreateJournal)

//        buttonCreateJournal.apply {
//            layoutParams = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT)
//            text = "Create journal"
//            setAllCaps(false)
//            textSize = 20f
//            id = R.id.btnCreateJournal
//        }

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


       // linearLayout.addView(buttonCreateJournal)
        // in the future will be more buttons with journals
    //    linearLayout.addView(buttonJournal1)



        gridview.onItemClickListener = AdapterView.OnItemClickListener {
                parent, v, position, id
                        -> startJournalActivity(position, id)
        }


        // Inflate the layout for this fragment
        return rootView
    }

//    TODO: customize
    private fun startJournalActivity(position:Int, id:Long) {
        val intent = Intent(getActivity(), JournalActivity::class.java).apply {
            // putExtra(EXTRA_MESSAGE, "HELLO")
        }
        startActivity(intent)
    }


    override fun onClick(v:View?) {
        when(v?.id) {
            R.id.btnCreateJournal -> {
                val intent = Intent(getActivity(), CreateJournalActivity::class.java).apply {
                    // putExtra(EXTRA_MESSAGE, "HELLO"
                }
                startActivity(intent)
            }
//            TODO: delete after test
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
