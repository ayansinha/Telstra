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
            emit(Resource.success(data = repository.getCountryFeatureTitle()))
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error! ${Constants.ERROR_MSG}"
                )
            )
        }
    }


    /**
     * fetch from coroutines io thread
     */
    /*fun fetchData() {
        job = CoroutinesIO.ioMain({
            repository.getCountryFeatureTitle()
        }) {
            model.value
        }
    }*/

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}

/*
class CountryViewModel(private val dataRepo: CountryRepository): ViewModel() {

    private val titleList = MutableLiveData<Resource<CountryDataModel>>()
    private val compositeDisposable = CompositeDisposable()

    */
/**
 * function to fetch data from server
 *//*

    fun fetchData() {
        titleList.postValue(Resource.loading(null))
        compositeDisposable.add(
            dataRepo.getCountryFeatureTitle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { list -> titleList.postValue(Resource.success(list))} ,
                    {t: Throwable? -> titleList.postValue(Resource.error(Constant.ERROR_MSG , null))}
        ))
    }

    */
/**
 * clears the view state
 *//*

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    */
/**
 * retrieving list to data model
 *//*

    fun fetchList(): LiveData<Resource<CountryDataModel>> {
        return titleList
    }


}*/