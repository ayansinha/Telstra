package org.techm.telstra.ui.country.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_country.*
import org.techm.telstra.R
import org.techm.telstra.data.model.CountryDataModel
import org.techm.telstra.data.model.Rows
import org.techm.telstra.data.network.APIHelper
import org.techm.telstra.data.network.RetrofitBuilder
import org.techm.telstra.ui.country.adapter.CountryAdapter
import org.techm.telstra.ui.country.viewmodel.CountryViewModel
import org.techm.telstra.ui.factory.CountryFactory
import org.techm.telstra.util.Constants
import org.techm.telstra.util.Status
import org.techm.telstra.util.toastShort

/**
 * @class{CountryActivity}
 */
class CountryActivity : AppCompatActivity() {

    private lateinit var countryViewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter
    private var dataModel: CountryDataModel? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var imageViewNoConnection: ImageView
    private var list: ArrayList<Rows>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        setupUI()
        setUpViewModel()
        setupAPICall()
        /**
         * swipe on refresh
         */
        swipeRefreshLayout.setOnRefreshListener {
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
            swipeRefreshLayout.isRefreshing = false
        }
    }


    /**
     * initialize ui elements
     */
    private fun setupUI() {
        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        imageViewNoConnection = findViewById(R.id.imageViewNoConnection)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CountryAdapter(arrayListOf() , this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
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
        countryViewModel.fetchCountryData().observe(this , Observer {
            it?.let {resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        resource.data?.let {it ->
                            supportActionBar?.title = it.body()?.title

                            if (list.isNullOrEmpty()) {
                                it.body()?.let { it -> retrieveCountryFeatureRows(it.rows) }
                                list = (it.body()?.rows)
                            }
                        }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        imageViewNoConnection.visibility = View.VISIBLE
                        toastShort(Constants.NO_CONNECTION)
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveCountryFeatureRows(rows: ArrayList<Rows>) {
        adapter.addData(rows)
        adapter.notifyDataSetChanged()
    }
}



