package com.example.gabriel.cryptocurrencies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gabriel.cryptocurrencies.data.Cryptocurrency

@Database(entities = [Cryptocurrency::class], version = 1, exportSchema = false)
abstract class CryptocurrencyDatabase : RoomDatabase() {
    abstract fun cryptocurrencyDao(): CryptocurrencyDao
}