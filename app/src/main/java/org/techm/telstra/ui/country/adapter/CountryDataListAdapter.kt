package org.techm.telstra.ui.country.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.techm.telstra.R
import org.techm.telstra.data.model.CountryDataItem


/**
 * @class{CountryAdapter}
 */

class CountryDataListAdapter(private var data: ArrayList<CountryDataItem>, var context: Context) :
    RecyclerView.Adapter<CountryDataItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryDataItemHolder =
        CountryDataItemHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_country,
                parent,
                false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CountryDataItemHolder, position: Int) {

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
    fun addData(list: ArrayList<CountryDataItem>) {

        for (dataItem in list) {
            if ((dataItem.title != null) || (dataItem.description != null) || (dataItem.imageHref != null)) {
                data.add(dataItem)
            }
        }

    }

    /**
     * removes data-source from list
     */
    fun removeData(list: ArrayList<CountryDataItem>) {
        data.removeAll(list)
    }
}