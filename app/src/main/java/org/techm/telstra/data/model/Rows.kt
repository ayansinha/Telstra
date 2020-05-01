package org.techm.telstra.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * data class for rows
 */
data class Rows (
    @SerializedName("title")
    @Expose
    val title : String? = null,

    @SerializedName("description")
    @Expose
    val description : String? = null,

    @SerializedName("imageHref")
    @Expose
    val imageHref : String? = null
)