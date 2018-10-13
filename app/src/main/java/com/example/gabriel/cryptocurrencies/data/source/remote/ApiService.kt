package com.example.gabriel.cryptocurrencies.data.source.remote

import com.example.gabriel.cryptocurrencies.data.Cryptocurrency
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("ticker/")
    fun getAll(
        @Query("limit") limit: Int,
        @Query("start") offset: Int
    ): Observable<List<Cryptocurrency>>
}