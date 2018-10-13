package com.example.gabriel.cryptocurrencies

import com.example.gabriel.cryptocurrencies.data.source.CryptocurrencyRepository
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class CryptocurrencyTest {
    @Mock
    lateinit var repository: CryptocurrencyRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }
}
