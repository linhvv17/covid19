package com.example.covid19.view

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19.R
import com.example.covid19.databinding.ActivityMainBinding
import com.example.covid19.model.Country
import com.example.covid19.viewmodel.CountryViewModel
import io.reactivex.disposables.Disposable


class MainActivity : AppCompatActivity(), CountryAdapter.CallBack {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: CountryViewModel
//    private var mDispose: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        model = CountryViewModel()
        binding.rcCountry.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter =
                CountryAdapter(this@MainActivity)
        }
        binding.data = model
        binding.lifecycleOwner = this
//        mDispose = model.getCountry()
//        mDispose!!.dispose()
        model.getCountry()
        model.countries.observe(this, object : Observer<MutableList<Country>> {
            override fun onChanged(t: MutableList<Country>?) {
                (binding.rcCountry.adapter as CountryAdapter).notifyDataSetChanged()
            }
        })

    }

//    override fun afterTextChanged(text: Editable) {
//        mDispose?.dispose()
//        mDispose = model.searchSong(binding.edtSearch.text.toString().trim())
//    }

//    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//    }
//
//    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//    }

    override fun onDestroy() {
//        mDispose?.dispose()
        super.onDestroy()
    }

//    override fun onClickItem(position: Int) {
//
//    }

    override fun getModel() = model
}