package com.example.gabriel.cryptocurrencies.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gabriel.cryptocurrencies.data.Cryptocurrency
import io.reactivex.Single

@Dao
interface CryptocurrencyDao {
    @Query("SELECT * FROM cryptocurrencies ORDER BY rank LIMIT :limit OFFSET :offset")
    fun fetchAll(limit: Int, offset: Int): Single<List<Cryptocurrency>>

    @Query("SELECT * from cryptocurrencies WHERE id = :id")
    fun fetchOneById(id: String): Single<Cryptocurrency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cryptocurrency: Cryptocurrency)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cryptocurrency: List<Cryptocurrency>)
}
