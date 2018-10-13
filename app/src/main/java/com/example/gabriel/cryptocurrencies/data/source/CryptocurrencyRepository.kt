package com.example.gabriel.cryptocurrencies.data.source

import com.example.gabriel.cryptocurrencies.data.Cryptocurrency
import com.example.gabriel.cryptocurrencies.data.source.local.CryptocurrencyDao
import com.example.gabriel.cryptocurrencies.data.source.remote.ApiService
import com.example.gabriel.cryptocurrencies.util.Util
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CryptocurrencyRepository @Inject constructor(private val api: ApiService, private val dao: CryptocurrencyDao, private val util: Util) {
    fun fetchAllCryptocurrencies(limit: Int, offset: Int): Observable<List<Cryptocurrency>> {
        if (!util.hasInternetConnection()) {
            return fetchAllFromDatabase(limit, offset)
        }

        return fetchAllFromApi(limit, offset)
    }

    fun fetchOneCryptocurrency(id: String): Single<Cryptocurrency> {
        return dao.fetchOneById(id)
    }

    private fun fetchAllFromApi(limit: Int, offset: Int): Observable<List<Cryptocurrency>> {
        return api.getAll(limit, offset).doOnNext {
            for (item in it) {
                dao.insert(item)
            }
        }
    }

    private fun fetchAllFromDatabase(limit: Int, offset: Int): Observable<List<Cryptocurrency>> {
        return dao.fetchAll(limit, offset).toObservable()
    }
}