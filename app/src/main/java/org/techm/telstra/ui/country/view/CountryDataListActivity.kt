package org.techm.telstra.ui.country.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_country.*
import org.techm.telstra.R
import org.techm.telstra.data.model.CountryDataModel
import org.techm.telstra.data.model.CountryDataItem
import org.techm.telstra.data.network.APIHelper
import org.techm.telstra.data.network.RetrofitBuilder
import org.techm.telstra.databinding.ActivityCountryBinding
import org.techm.telstra.ui.country.adapter.CountryDataListAdapter
import org.techm.telstra.ui.country.viewmodel.CountryViewModel
import org.techm.telstra.ui.factory.CountryFactory
import org.techm.telstra.util.*

/**
 * @class{CountryActivity}
 */
class CountryDataListActivity : AppCompatActivity() {

    private lateinit var countryViewModel: CountryViewModel
    private lateinit var adapter: CountryDataListAdapter
    private var dataModel: CountryDataModel? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var imageViewNoConnection: ImageView
    private var list: ArrayList<CountryDataItem>? = null
    private lateinit var dataBinding: ActivityCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this , R.layout.activity_country)
        setupUI()
        setUpViewModel()
        if (isConnection()) {
            setupAPICall()
        }else {
            toastShort(Constants.NO_CONNECTION)
            dataBinding.recyclerView.visibility = View.VISIBLE
            dataBinding.progressBar.visibility = View.GONE
            dataBinding.imageViewNoConnection.visibility = View.VISIBLE
        }

        /**
         * swipe on refresh
         */
        dataBinding.swipeRefreshLayout.setOnRefreshListener {
            /**
             * if data-source is empty or no internet connection when application is on background
             */
            if (list.isNullOrEmpty() || list?.size == 0) {
                setupAPICall()
            } else {
                adapter.removeData(list!!)
                retrieveCountryFeatureRows(list!!)
            }
            adapter.notifyDataSetChanged()
            dataBinding.swipeRefreshLayout.isRefreshing = false
        }
    }
    /**
     * initialize ui elements
     */
    private fun setupUI() {
        dataBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CountryDataListAdapter(arrayListOf() , this)
        dataBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                dataBinding.recyclerView.context,
                (dataBinding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        dataBinding.recyclerView.adapter = adapter
    }
    private fun setUpViewModel() {
        countryViewModel =
            ViewModelProviders.of(this, CountryFactory(APIHelper(RetrofitBuilder.apiService)))
                .get(CountryViewModel::class.java)
    }
    /**
     * setUpObservers
     */
    private fun setupAPICall() {
        countryViewModel.getCountryModel().observe(this , Observer {
            it?.let {resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        dataBinding.progressBar.visibility = View.GONE
                        dataBinding.recyclerView.visibility = View.VISIBLE
                        resource.data?.let {it ->
                            supportActionBar?.title = it.body()?.title
                            if (list.isNullOrEmpty()) {
                                it.body()?.let { it -> retrieveCountryFeatureRows(it.dataItem) }
                                list = (it.body()?.dataItem)
                            }
                        }
                    }
                    Status.ERROR -> {
                        dataBinding.recyclerView.visibility = View.VISIBLE
                        dataBinding.progressBar.visibility = View.GONE
                        dataBinding.imageViewNoConnection.visibility = View.VISIBLE
                        dataBinding.recyclerView.showSnackBar(Constants.ERROR_MSG)
                    }
                    Status.LOADING -> {
                        dataBinding.progressBar.visibility = View.VISIBLE
                        dataBinding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveCountryFeatureRows(dataItem: ArrayList<CountryDataItem>) {
        adapter.addData(dataItem)
        adapter.notifyDataSetChanged()
    }
}