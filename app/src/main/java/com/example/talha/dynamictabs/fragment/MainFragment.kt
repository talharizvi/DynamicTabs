package com.example.talha.dynamictabs.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.talha.dynamictabs.Api.ApiClient
import com.example.talha.dynamictabs.Api.ApiInterface
import com.example.talha.dynamictabs.R
import com.example.talha.dynamictabs.adapter.MainFragAdapter
import com.example.talha.dynamictabs.model.ArticleData
import com.example.talha.dynamictabs.model.NewsDataApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment:Fragment() {
    lateinit var apiInterface:ApiInterface
    var apiKey ="9bdc3eebf3d34aa099ab65815f3d75ed"
    var articleList = ArrayList<ArticleData>()
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        apiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.layout_main_fragment,container,false)
        var tView = view.findViewById<TextView>(R.id.tv_main_frag)
        var recyclerView = view.findViewById<RecyclerView>(R.id.rv_frag)
        var layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
        tView.setText("MainFragemet")
        var fragAdapter = MainFragAdapter(context)
        var country = "us"
        var call = apiInterface.getNewsFromApi(country,apiKey)
        call.enqueue(object :Callback<NewsDataApi>{
            override fun onFailure(call: Call<NewsDataApi>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<NewsDataApi>?, response: Response<NewsDataApi>?) {
                 //To change body of created functions use File | Settings | File Templates.
                var result = response?.body()
                 articleList = result!!.articles
                fragAdapter.setData(articleList)
                 fragAdapter.notifyDataSetChanged()
            }

        })

        recyclerView.adapter = fragAdapter
        return view
    }
}