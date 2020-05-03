package org.techm.telstra.ui.country.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.techm.telstra.R
import org.techm.telstra.data.model.Rows


/**
 * @class{CountryAdapter}
 */

class CountryAdapter(private var data: ArrayList<Rows>, var context: Context) :
    RecyclerView.Adapter<CountryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder =
        CountryHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_country,
                parent,
                false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {

        holder.itemCountryBinding.country = data[position]
        holder.itemCountryBinding.cardViewContainer.setAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.fade_scale_animation
            )
        )
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