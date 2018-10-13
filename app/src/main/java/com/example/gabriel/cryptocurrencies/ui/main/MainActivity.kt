package com.example.gabriel.cryptocurrencies.ui.main

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gabriel.cryptocurrencies.R
import com.example.gabriel.cryptocurrencies.data.Cryptocurrency
import com.example.gabriel.cryptocurrencies.ui.main.viewmodel.CryptocurrencyViewModel
import com.example.gabriel.cryptocurrencies.ui.main.viewmodel.CryptocurrencyViewModelFactory
import com.example.gabriel.cryptocurrencies.util.*
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickListener {
    @Inject
    lateinit var cryptocurrencyViewModelFactory: CryptocurrencyViewModelFactory
    private lateinit var cryptocurrencyViewModel: CryptocurrencyViewModel
    private var mainAdapter = MainAdapter(ArrayList())
    private val gridLayoutManager = GridLayoutManager(this, 3)
    private var currentPage: Int = 0
    private var start: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        initialize()
        loadData()
        handleData()
    }

    override fun onItemClick(cryptocurrency: Cryptocurrency, field: View) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(EXTRA_CRYPTOCURRENCY_DETAILS, cryptocurrency.name)
        intent.putExtra(EXTRA_TRANSITION_NAME, cryptocurrency.id)

        val transitionName = ViewCompat.getTransitionName(field)
        val options = ActivityOptions.makeSceneTransitionAnimation(this, field, transitionName)
        startActivity(intent, options.toBundle())
    }

    private fun initialize() {
        cryptocurrencyViewModel = ViewModelProviders.of(this, cryptocurrencyViewModelFactory).get(CryptocurrencyViewModel::class.java)
        pb_loading?.visibility = View.VISIBLE

        rv_cryptocurrencies?.apply {
            layoutManager = gridLayoutManager
            adapter = mainAdapter
            setHasFixedSize(true)
            addOnScrollListener(InfiniteScrollListener({ loadData() }, gridLayoutManager))
        }

        mainAdapter.onItemClickListener = this
    }

    private fun loadData() {
        start = QUERY_START

        if (currentPage > 0) {
            start = currentPage * QUERY_LIMIT
        }

        cryptocurrencyViewModel.loadAllCryptocurrencies(QUERY_LIMIT, start)
        currentPage++
    }

    private fun handleData() {
        cryptocurrencyViewModel.loading().observe(this, Observer<Boolean> {
            if (it == false) {
                pb_loading.visibility = View.GONE
            }
        })

        cryptocurrencyViewModel.result().observe(this, Observer<List<Cryptocurrency>> {
            if (it != null) {
                mainAdapter.addData(it)
            }
        })

        cryptocurrencyViewModel.error().observe(this, Observer<String> {
            if (it != null) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }
}
