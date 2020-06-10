package com.example.traveljournal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.traveljournal.R
import com.example.traveljournal.data.database.Journal

internal class JournalsGridAdapter internal constructor(context: Context, private val resource:Int)
    : ArrayAdapter<JournalsGridAdapter.ItemHolder>(context, resource) {

    var itemList = emptyList<Journal>()

    private val inflater:LayoutInflater = LayoutInflater.from(context)
    private var journals = emptyList<Journal>()


    override fun getCount(): Int {
        return if(this.itemList != null) this.itemList.size else 0
    }
    override fun getView(position:Int, convertView: View?, parent: ViewGroup) : View {
        var convertView = convertView
        var holder: ItemHolder
        if(convertView==null) {
            convertView = LayoutInflater.from(context).inflate(resource, null)
            holder =
                ItemHolder()
            holder.name = convertView!!.findViewById(R.id.thumbnailTextView)
            holder.icon = convertView.findViewById(R.id.thumbnailIcon)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemHolder
        }

        holder.name!!.text= this.itemList.get(position).name
        holder.icon!!.setImageResource(R.drawable.traveljournal5)

        return convertView
    }

    internal class ItemHolder() {
        var name: TextView? = null
        var icon: ImageView? = null
    }

    internal  fun setJournals(journals: List<Journal> ) {
        this.itemList = journals
        notifyDataSetChanged()
    }
}