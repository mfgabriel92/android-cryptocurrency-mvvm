package com.example.gabriel.cryptocurrencies.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gabriel.cryptocurrencies.data.Cryptocurrency
import com.example.gabriel.cryptocurrencies.data.source.CryptocurrencyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CryptocurrencyViewModel @Inject constructor(private val repository: CryptocurrencyRepository) : ViewModel() {
    lateinit var disposable: DisposableObserver<List<Cryptocurrency>>

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var result: MutableLiveData<List<Cryptocurrency>> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()

    fun loadAllCryptocurrencies(limit: Int, offset: Int) {
        disposable = object : DisposableObserver<List<Cryptocurrency>>() {
            override fun onComplete() {
            }

            override fun onNext(critpcurrencies: List<Cryptocurrency>) {
                result.postValue(critpcurrencies)
                loading.postValue(false)
            }

            override fun onError(e: Throwable) {
                error.postValue(e.message)
                loading.postValue(false)
            }
        }

        repository.fetchAllCryptocurrencies(limit, offset)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposable)
    }

    fun loading(): LiveData<Boolean> = loading
    fun result(): LiveData<List<Cryptocurrency>> = result
    fun error(): LiveData<String> = error
}