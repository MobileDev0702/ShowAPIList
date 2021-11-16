package com.codechallenge.apilist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codechallenge.apilist.R
import com.codechallenge.apilist.api.ApiInterface
import com.codechallenge.apilist.model.ApiListAdapter
import com.codechallenge.apilist.model.ItemsViewModel
import com.codechallenge.apilist.util.LoadingIndicator
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var apiData = ArrayList<ItemsViewModel>()
    lateinit var apiAdapter: ApiListAdapter
    lateinit var loadingIndicator: LoadingIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initLoadingIndicator()
        getApiData()
        initAPIListReyclerView()
    }

    private fun initLoadingIndicator() {
        loadingIndicator = LoadingIndicator().getInstance()!!
    }

    private fun getApiData() {
        loadingIndicator.showProgress(this)
        val apiInterface = ApiInterface.create().getApiData()

        apiInterface.enqueue(object : Callback<JsonObject> {
            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                if (response.body() != null) {
                    val respObject = JSONObject(response.body().toString())
                    val jsonArray = respObject.getJSONArray("entries")
                    for (i in 0 until jsonArray.length()) {
                        val dataObject = jsonArray.getJSONObject(i)
                        val api = dataObject.getString("API")
                        val desc = dataObject.getString("Description")
                        val auth = dataObject.getString("Auth")
                        val https = dataObject.getString("HTTPS")
                        val cors = dataObject.getString("Cors")
                        val link = dataObject.getString("Link")
                        val category = dataObject.getString("Category")
                        if (category.equals("Games & Comics")) {
                            apiData.add(ItemsViewModel(api, desc, auth, https, cors, link, category))
                        }
                    }
                    apiAdapter.notifyDataSetChanged()
                    loadingIndicator.hideProgress()
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("Failure", t.localizedMessage)
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
                loadingIndicator.hideProgress()
            }
        })
    }

    private fun initAPIListReyclerView() {
        val rv_apilist = findViewById<RecyclerView>(R.id.rv_apilist)
        rv_apilist.layoutManager = LinearLayoutManager(this)
        apiAdapter = ApiListAdapter(apiData)
        rv_apilist.adapter = apiAdapter
    }
}