package com.example.covid19

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_country.view.*

class CountryAdapter: RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
//    private var context: Context
    private var countries: ArrayList<Country>
    constructor( countries: ArrayList<Country>){
//        this.context = context
        this.countries = countries
    }
    class CountryViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var ic_flag: ImageView
        var tv_name_country: TextView
        var tv_country_infected: TextView
        var tv_country_die: TextView
        var tv_country_cure: TextView
        var tv_country_heal: TextView
        init {
            ic_flag = itemView.findViewById(R.id.ic_flag)
            tv_name_country = itemView.findViewById(R.id.tv_name_country)
            tv_country_infected = itemView.findViewById(R.id.tv_country_infected)
            tv_country_die = itemView.findViewById(R.id.tv_country_die)
            tv_country_cure = itemView.findViewById(R.id.tv_country_cure)
            tv_country_heal = itemView.findViewById(R.id.tv_country_heal)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.row_country, parent, false
        )
        return CountryViewHolder(itemView)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val data = countries.get(position)
        holder.tv_name_country.text = data.country
        holder.tv_country_infected.text = data.cases
        holder.tv_country_die.text = data.deaths
        holder.tv_country_cure.text = data.active
        holder.tv_country_heal.text = data.recovered
        Glide.with(holder.ic_flag)
            .load(data.flag)
            .placeholder(R.drawable.ic_vietnam)
            .error(R.drawable.ic_vietnam)
            .into(holder.ic_flag)
    }
}