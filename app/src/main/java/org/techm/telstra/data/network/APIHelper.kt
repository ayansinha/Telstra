package org.techm.telstra.data.network

import org.techm.telstra.data.network.RetrofitBuilder.apiService

/**
 * @class{APIHelper}
 */
class APIHelper(apiService: APIService) {

    suspend fun getUsers() = apiService.getCountryFeature()
}