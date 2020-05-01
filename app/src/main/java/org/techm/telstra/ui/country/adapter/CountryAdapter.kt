package org.techm.telstra.ui.country.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*
import org.techm.telstra.R
import org.techm.telstra.data.model.Rows


/**
 * @class{CountryAdapter}
 */

class CountryAdapter(private var data: ArrayList<Rows>) : RecyclerView.Adapter<CountryHolder>() {

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

        Glide.with(holder.itemImage.context)
            .load(data[position].imageHref)
            //.placeholder(R.drawable.no_image_icon)
            .error(R.drawable.no_image_icon)
            //.fallback(R.drawable.no_image_icon)
            //.diskCacheStrategy(DiskCacheStrategy.DATA)
            //.transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.itemImage.imageViewPic)

        /*} else {

            Glide.with(holder.itemImage.context)
                //.load(data[position].imageHref)
                .load(R.drawable.no_image_icon)
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                //.fallback(R.drawable.no_image_icon)
                .placeholder(R.drawable.no_image_icon)
                //.error(R.drawable.no_image_icon)
                //.transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.itemImage.imageViewPic)
        }*/
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
     * remove data-source from list
     */
    fun removeData(list: ArrayList<Rows>) = data.removeAll(list)

}