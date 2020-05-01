package org.techm.telstra.data.component

import org.techm.telstra.data.network.APIHelper


/**
 * @class{CountryRepository}
 */
class CountryRepository(private val apiHelper: APIHelper) {

    suspend fun getCountryFeatureTitle() = apiHelper.getUsers()

}