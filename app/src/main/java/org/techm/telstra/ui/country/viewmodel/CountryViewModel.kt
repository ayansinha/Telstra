package org.techm.telstra.ui.country.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.techm.telstra.data.component.CountryRepository
import org.techm.telstra.data.model.CountryDataModel
import org.techm.telstra.util.Constants
import org.techm.telstra.util.Resource


/**
 * @class{CountryViewModel}
 */
class CountryViewModel(private val repository: CountryRepository) : ViewModel() {
    private lateinit var job: Job

    private val model = MutableLiveData<CountryDataModel>()
    val modelData: LiveData<CountryDataModel>
        get() = model


    fun fetchCountryData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(
                data = repository.getCountryFeatureTitle()))
        }catch (exception: Exception) {
            emit(Resource.error(data = null , message = exception.message ?: "Error! ${Constants.ERROR_MSG}"))
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}

