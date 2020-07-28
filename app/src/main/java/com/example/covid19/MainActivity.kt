package com.example.covid19

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity(){
    private lateinit var rcCountry:RecyclerView
    private var countries = arrayListOf<Country>()
    var countryAdapter = CountryAdapter(countries)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setTheWorld()
        createAsy()
        rcCountry = findViewById(R.id.rc_country)
        rcCountry.layoutManager= LinearLayoutManager(this)
        rcCountry.adapter=countryAdapter
    }
    private fun createAsy() {
        val asy = object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void): Void? {
                setListView()
                setTheWorld()
                return null
            }

            override fun onPostExecute(result: Void?) {
                rcCountry.adapter?.notifyDataSetChanged()
            }
        }
        asy.execute()
    }

    private fun setTheWorld() {
        val requestQueue = Volley.newRequestQueue(this@MainActivity)
        val url = "https://disease.sh/v3/covid-19/all"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                tv_die.text = response.getString("deaths")
                tv_cure.text = response.getString("active")
                tv_heal.text = response.getString("recovered")
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )
        requestQueue.add(jsonObjectRequest)

// Access the RequestQueue through your singleton class.
//        World.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }


    private fun setListView() {
//        countries.add(
//            Country("Việt Nam",R.drawable.ic_vietnam,
//        "333","0","333","0")
//        )
//        countries.add(Country("Nam Việt",R.drawable.ic_vietnam,
//            "333","0","333","0"))
        val requestQueue = Volley.newRequestQueue(this@MainActivity)
        val url = "https://disease.sh/v2/countries"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String?> { response ->
                if (response != null) {
                    try {
                        val jsonArrayList = JSONArray(response)
                        for (i in 0 until jsonArrayList.length()) {
                            val jsonObject = jsonArrayList.getJSONObject(i)
                            val peopleDie = jsonObject.getString("deaths")
//                            Log.d("ABCD",peopleDie)
                            val peopleMask = jsonObject.getString("active")
                            val people = jsonObject.getString("recovered")
                            val peopleSum = jsonObject.getString("cases")
                            val country = jsonObject.getString("country")
                            val countryInfo =
                                JSONObject(jsonObject.getString("countryInfo"))
                            val flag = countryInfo.getString("flag")

                            countries?.add(
                                Country(
                                    country,
                                    flag,
                                    peopleSum,
                                    peopleDie,
                                    people,
                                    peopleMask
                                )
                            )
                            countryAdapter?.notifyDataSetChanged()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            },
            Response.ErrorListener { })
        requestQueue.add(stringRequest)
    }
}