package com.example.talha.dynamictabs.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.talha.dynamictabs.R
import com.example.talha.dynamictabs.adapter.MainScreenAdapter

class MainScreenActivity:AppCompatActivity() {
    lateinit var vPager:ViewPager
    lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        vPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)
        var mainScreenAdapterObj = MainScreenAdapter(supportFragmentManager)
        vPager.adapter = mainScreenAdapterObj
        tabLayout.setupWithViewPager(vPager)
    }

    fun setViewPager(){

    }
}