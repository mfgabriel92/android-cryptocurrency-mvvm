package com.example.gabriel.cryptocurrencies.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "cryptocurrencies")
data class Cryptocurrency(
    @PrimaryKey
    val id: String,

    val name: String,

    val symbol: String,

    val rank: Int,

    @ColumnInfo(name = "price_usd")
    @Json(name = "price_usd")
    val priceUsd: Double?,

    @ColumnInfo(name = "price_btc")
    @Json(name = "price_btc")
    val priceBtc: String?,

    @ColumnInfo(name = "24h_volume_usd")
    @Json(name = "24h_volume_usd")
    val volumeUsd24h: String?,

    @ColumnInfo(name = "market_cap_usd")
    @Json(name = "market_cap_usd")
    val marketCapUsd: String?,

    @ColumnInfo(name = "available_supply")
    @Json(name = "available_supply")
    val availableSupply: String?,

    @ColumnInfo(name = "total_supply")
    @Json(name = "total_supply")
    val totalSupply: String?,

    @ColumnInfo(name = "max_supply")
    @Json(name = "max_supply")
    val maxSupply: String?,

    @ColumnInfo(name = "percent_change_1h")
    @Json(name = "percent_change_1h")
    val percentChange1h: String?,

    @ColumnInfo(name = "percent_change_24h")
    @Json(name = "percent_change_24h")
    val percentChange24h: String?,

    @ColumnInfo(name = "percent_change_7d")
    @Json(name = "percent_change_7d")
    val percentChange7d: String?,

    @ColumnInfo(name = "last_updated")
    @Json(name = "last_updated")
    val lastUpdated: Double
) : Serializable