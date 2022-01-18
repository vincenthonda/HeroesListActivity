package com.example.heroeslistactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class HeroAdapter {
    // change CustomAdapter to HeroAdapter
// change Array<String> to List<Hero>
// change private val dataset to var dataset so we can do sorting later
// make sure your ids for the text views are named well...
// in the ViewHolder inner class, declare your instance variables for your widgets & wire them
// make sure in onCreateViewHolder to change the layout you inflate to the item layout you made
// in the onBindViewHolder: given the ViewHolder and the position in the list.
    // get the the item from the dataset at that position
    // match up those item's attributes to the various textViews or other widgets in the ViewHolder

    class HeroAdapter(var dataSet: List<Hero>) :
        RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val heroRanking: TextView
            val heroName: TextView
            val heroDesc : TextView
            val layout : ConstraintLayout

            init {
                heroRanking = view.findViewById(R.id.textView_heroItem_ranking)
                heroName = view.findViewById(R.id.textView_heroItem_name)
                heroDesc = view.findViewById(R.id.textView_heroItem_desc)
                layout = view.findViewById(R.id.layout_heroItem)
            }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_hero, viewGroup, false)

            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            val hero = dataSet[position]
            viewHolder.heroRanking.text = hero.ranking.toString()
            viewHolder.heroName.text = hero.name
            viewHolder.heroDesc.text = hero.description
            viewHolder.layout.setOnClickListener {
                Toast.makeText(it.context, "Hi, you clicked on ${hero.name}", Toast.LENGTH_SHORT).show()
            }
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size
    }
}