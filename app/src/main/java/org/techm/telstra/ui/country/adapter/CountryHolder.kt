package org.techm.telstra.ui.country.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.techm.telstra.R


class CountryHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var itemImage: ImageView = itemView.findViewById(R.id.imageViewPic)
    var itemTitle: TextView = itemView.findViewById(R.id.textViewTitle)
    var itemDescription: TextView = itemView.findViewById(R.id.textViewDescription)

}