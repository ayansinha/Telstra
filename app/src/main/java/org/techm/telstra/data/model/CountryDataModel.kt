package org.techm.telstra.data.model

import com.google.gson.annotations.SerializedName


/**
 * @class{CountryDataModel}
 */
data class CountryDataModel(
    @SerializedName("title")
    val title: String = "",

    @SerializedName("rows")
    val rows : ArrayList<Rows>
)