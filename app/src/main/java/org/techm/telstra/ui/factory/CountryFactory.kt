package org.techm.telstra.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.techm.telstra.data.network.APIHelper
import org.techm.telstra.ui.country.viewmodel.CountryViewModel


/**
 * @class{CountryFactory}
 */
class CountryFactory(private val apiHelper: APIHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}