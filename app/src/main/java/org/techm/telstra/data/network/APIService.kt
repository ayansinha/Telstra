package org.techm.telstra.data.network

import org.techm.telstra.data.model.CountryDataModel
import retrofit2.Response
import retrofit2.http.GET

/**
 * @interface{APIService}
 */
interface APIService {

    @GET("facts.json")
    suspend fun getCountryFeature(): Response<CountryDataModel>
}