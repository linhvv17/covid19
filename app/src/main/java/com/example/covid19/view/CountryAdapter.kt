package com.example.covid19.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.databinding.RowCountryBinding
import com.example.covid19.viewmodel.CountryViewModel


class CountryAdapter(private val callBack: MainActivity):
    RecyclerView.Adapter<CountryAdapter.CountryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        return CountryHolder(
            RowCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount():Int{
        if(  callBack.getModel().countries.value == null){
            return 0
        }else{
            return callBack.getModel().countries.value!!.size
        }
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.binding.itemData=callBack.getModel().countries.value!![position]
//        Log.d("AAA", holder.binding.itemData.cases.toString())
//        holder.binding.root.setOnClickListener {
//            callBack.onClickItem(
//                holder.adapterPosition
//            )
//        }
    }


    interface CallBack{
//        fun onClickItem(position:Int)
        fun getModel():CountryViewModel
    }

    class CountryHolder(val binding:RowCountryBinding) :RecyclerView.ViewHolder(binding.root)

}