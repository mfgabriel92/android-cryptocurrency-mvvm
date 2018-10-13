package com.example.gabriel.cryptocurrencies.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gabriel.cryptocurrencies.R
import com.example.gabriel.cryptocurrencies.data.Cryptocurrency
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.currency_item.view.*
import java.text.NumberFormat

class MainAdapter(list: List<Cryptocurrency>?) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private var cryptocurrenciesList = ArrayList<Cryptocurrency>()
    var onItemClickListener: OnItemClickListener? = null

    init {
        this.cryptocurrenciesList = list as ArrayList<Cryptocurrency>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.currency_item, parent, false)

        return MainViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cryptocurrenciesList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val cryptocurrency = cryptocurrenciesList[position]
        val tvCryptocurrencyName = holder.itemView.tv_cryptocurrency_name

        holder.populateUi(cryptocurrency)
        holder.itemView.setOnClickListener { onItemClickListener?.onItemClick(cryptocurrenciesList[position], tvCryptocurrencyName) }
        ViewCompat.setTransitionName(tvCryptocurrencyName, cryptocurrenciesList[position].id)
    }

    fun addData(cryptocurrencies: List<Cryptocurrency>) {
        val pos = cryptocurrenciesList.size
        cryptocurrenciesList.addAll(cryptocurrencies)
        notifyItemRangeInserted(pos, cryptocurrenciesList.size)
    }

    interface OnItemClickListener {
        fun onItemClick(cryptocurrency: Cryptocurrency, field: View)
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val formatter = NumberFormat.getCurrencyInstance()!!

        fun populateUi(cryptocurrency: Cryptocurrency) {
            itemView.findViewById<TextView>(R.id.tv_cryptocurrency_name).text = cryptocurrency.name
            itemView.findViewById<TextView>(R.id.txt_symbol).text = cryptocurrency.symbol
            itemView.findViewById<TextView>(R.id.txt_price_usd).text = formatter.format(cryptocurrency.priceUsd)
        }
    }
}