package com.example.talha.dynamictabs.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import com.example.talha.dynamictabs.Api.ApiClient
import com.example.talha.dynamictabs.Api.ApiInterface
import com.example.talha.dynamictabs.R
import com.example.talha.dynamictabs.VerticalViewpager
import com.example.talha.dynamictabs.adapter.DetailNewsAdapter
import com.example.talha.dynamictabs.model.ArticleData
import com.example.talha.dynamictabs.model.NewsDataApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity:AppCompatActivity(),VerticalViewpager.SwipeListener {

    lateinit var vPagerDetail:VerticalViewpager
    lateinit var apiInterface:ApiInterface
    var apiKey ="9bdc3eebf3d34aa099ab65815f3d75ed"
    var list= ArrayList<ArticleData>()
    var articleList = ArrayList<ArticleData>()
    lateinit var adapter:DetailNewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        apiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)
        var country = "us"
        var call = apiInterface.getNewsFromApi(country,apiKey)
        call.enqueue(object:Callback<NewsDataApi>{
            override fun onFailure(call: Call<NewsDataApi>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<NewsDataApi>?, response: Response<NewsDataApi>?) {
                 //To change body of created functions use File | Settings | File Templates.
                var result = response?.body()
                articleList = result!!.articles
               // adapter = DetailNewsAdapter(applicationContext)
                adapter.setData(articleList)

            }

        })


        vPagerDetail = findViewById(R.id.vPager_detail)
        adapter =DetailNewsAdapter(this)
        adapter.setData(articleList)
        vPagerDetail.adapter = adapter

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        vPagerDetail.onTouchEvent(event)
        Log.d("swipe_from_activity","ontouch event activity")
        return super.onTouchEvent(event)
    }

    override fun leftSwipe() {
        Log.d("swipe_from_left_swipe","ontouch event activity")

    }
}