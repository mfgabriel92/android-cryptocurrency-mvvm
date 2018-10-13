package com.example.gabriel.cryptocurrencies.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import com.example.gabriel.cryptocurrencies.R
import com.example.gabriel.cryptocurrencies.data.Cryptocurrency
import com.example.gabriel.cryptocurrencies.util.EXTRA_CRYPTOCURRENCY_DETAILS
import com.example.gabriel.cryptocurrencies.util.EXTRA_TRANSITION_NAME

class DetailActivity : AppCompatActivity() {
    lateinit var cryptocurrency: Cryptocurrency
    private var tvCryptocurrencyDetailName: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvCryptocurrencyDetailName = findViewById(R.id.tv_cryptocurrency_detail_name)
        tvCryptocurrencyDetailName?.transitionName = intent.getStringExtra(EXTRA_TRANSITION_NAME)
        tvCryptocurrencyDetailName?.text = intent.getStringExtra(EXTRA_CRYPTOCURRENCY_DETAILS)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
