package org.techm.telstra.ui.country.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.techm.telstra.R
import org.techm.telstra.data.model.Rows


/**
 * @class{CountryAdapter}
 */

class CountryAdapter(private var data: ArrayList<Rows>) : RecyclerView.Adapter<CountryHolder>() {

    private var uri: Uri? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
    )

    /**
     * get count of item
     */
    override fun getItemCount(): Int = data.size

    /**
     * binds views to item layout xml
     */
    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.itemTitle.text = data[position].title
        holder.itemDescription.text = data[position].description

        Picasso.get()
            .load(data[position].imageHref)
            .placeholder(R.drawable.no_image_icon)
            .error(R.drawable.no_image_icon)
            .into(holder.itemImage)
    }

    /**
     * add data-source to list
     */
    fun addData(list: ArrayList<Rows>) {

        for (rows in list) {
            if ((rows.title != null) || (rows.description != null) || (rows.imageHref != null)) {
                data.add(rows)
            }
        }

    }

    /**
     * removes data-source from list
     */
    fun removeData(list: ArrayList<Rows>) {
        data.removeAll(list)
    }




}