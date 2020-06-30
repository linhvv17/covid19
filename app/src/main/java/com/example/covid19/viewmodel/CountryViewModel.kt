package com.example.covid19.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.example.covid19.model.Country
import com.example.covid19.repository.CountryRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryViewModel {
    val isLoading = ObservableBoolean(false)
    private val repository: CountryRepository
    val countries = MutableLiveData<MutableList<Country>>()
    init {
        repository = Retrofit.Builder()
            .baseUrl("https://disease.sh/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.create()
            ).build()
            .create(CountryRepository::class.java)

    }
    fun getCountry(): Disposable {
        isLoading.set(true)
        return repository.
        getAllCountry()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //thanh cong
                isLoading.set(false)
                countries.value = it
            }, {
                //that bai
                isLoading.set(false)
            })
    }

}