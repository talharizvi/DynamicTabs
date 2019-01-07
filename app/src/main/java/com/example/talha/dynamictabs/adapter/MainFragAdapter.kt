package com.example.talha.dynamictabs.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.talha.dynamictabs.R
import com.example.talha.dynamictabs.model.ArticleData

class MainFragAdapter(var context:Context?):RecyclerView.Adapter<MainFragAdapter.MyViewHolder>() {
    var articleList = ArrayList<ArticleData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.list_item_frag,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
         //To change body of created functions use File | Settings | File Templates.
        return articleList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         //To change body of created functions use File | Settings | File Templates.
        holder.newsHeading.text = articleList.get(position).title
        holder.newsDesc.text = articleList.get(position).description
    }

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var newsHeading = itemView.findViewById<TextView>(R.id.news_head)
        var newsDesc = itemView.findViewById<TextView>(R.id.newsDesc)
    }

    fun setData(list: ArrayList<ArticleData>){
        articleList = list
    }
}