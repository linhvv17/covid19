package com.example.covid19.repository

import androidx.lifecycle.MutableLiveData
import com.example.covid19.model.Country
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query


interface CountryRepository {
    @GET("countries")
    fun getAllCountry(
    ): Observable<MutableList<Country>>
}