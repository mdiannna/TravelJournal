package com.example.traveljournal.adapters
import com.example.traveljournal.data.models.Location
//import com.example.traveljournal.util.loadImage



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traveljournal.R
import kotlinx.android.synthetic.main.item_location.view.*


class LocationListAdapter(val locations:ArrayList<Location>) :
    RecyclerView.Adapter<LocationListAdapter.LocationViewHolder>() {

    fun updateLocations(newLocations: List<Location>) {
        locations.clear();
        locations.addAll(newLocations);
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ) =
        LocationViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_location,
                parent, false
            )
        )

    override fun getItemCount(): Int {
        return locations.size
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(locations[position])
    }
    class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.imageViewLocation
        private val locationName = view.name
        private val locationCountry = view.country

        fun bind(country: Location) {
            locationName.text = country.name
            locationCountry.text = country.country
//            TODO: later
//            imageView.loadImage(country.image)

        }

    }
}