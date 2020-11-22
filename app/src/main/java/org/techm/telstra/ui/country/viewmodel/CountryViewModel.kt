package org.techm.telstra.ui.country.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.techm.telstra.data.model.CountryDataModel
import org.techm.telstra.data.network.APIHelper
import org.techm.telstra.util.Constants
import org.techm.telstra.util.Resource
import retrofit2.Response


/**
 * @class{CountryViewModel}
 */
class CountryViewModel(private val apiHelper: APIHelper) : ViewModel() {
    private lateinit var job: Job
    private val countryModel = MutableLiveData<Resource<Response<CountryDataModel>>>()
    init { fetchCountryData() }
    private fun fetchCountryData() {
        viewModelScope.launch(IO + job) {
            countryModel.postValue(Resource.loading(null))
            try {
                countryModel.postValue(Resource.success(apiHelper.getCountry()))
            } catch (exception: Exception) {
                countryModel.postValue(Resource.error(Constants.ERROR_MSG , null))
                exception.message ?: "Error! ${Constants.ERROR_MSG}"
            }
        }
    }
    fun getCountryModel(): LiveData<Resource<Response<CountryDataModel>>> {
        return countryModel
    }
    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}

