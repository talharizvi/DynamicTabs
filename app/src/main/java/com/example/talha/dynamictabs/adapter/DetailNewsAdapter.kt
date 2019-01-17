package com.example.talha.dynamictabs.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.talha.dynamictabs.R
import com.example.talha.dynamictabs.model.ArticleData
import kotlinx.android.synthetic.main.news_detail_layout.view.*

class DetailNewsAdapter(val context:Context):PagerAdapter() {

    var list = ArrayList<ArticleData>()
    lateinit var mLayoutInflater:LayoutInflater
    lateinit var newsHead: TextView
    lateinit var newsDesc:TextView
    lateinit var newsSource:TextView
//    init {
//        this.list = listNews
//    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
      //  return super.instantiateItem(container, position)
        mLayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = mLayoutInflater.inflate(R.layout.news_detail_layout,container,false)
        newsHead = view.findViewById(R.id.news_head_detail)
        newsDesc = view.findViewById(R.id.newsDesc_detail)
        newsSource = view.findViewById(R.id.news_source)
        newsHead.text = list.get(position).title
        newsDesc.text = list.get(position).description
        newsSource.text = list.get(position).author
        newsDesc.setOnClickListener{
            view.news_source.text = "Description clicked"
            Log.d("swipe_click_newsdesc","item clicked")
        }

        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
      //To change body of created functions use File | Settings | File Templates.
        return view === `object` as ConstraintLayout
    }

    override fun getCount(): Int {
         //To change body of created functions use File | Settings | File Templates.
        return list.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    fun setData(result: ArrayList<ArticleData>){
        list = result
        notifyDataSetChanged()
    }
}