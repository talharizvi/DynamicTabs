package com.example.talha.dynamictabs.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.talha.dynamictabs.fragment.MainFragment
import com.example.talha.dynamictabs.fragment.MainFragment2

class MainScreenAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {




    override fun getItem(position: Int): Fragment {

        var frag = MainFragment()
        var frag2 = MainFragment2()
//        return frag
        return if (position==0){
           frag
        }else if (position==1){
            frag2
        }else if (position==2){
            frag
        }else frag2
    }

    override fun getCount(): Int {
         //To change body of created functions use File | Settings | File Templates.
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (position ==0){
            return "One"
        }else if (position == 1){
            return "Two"
        }else if (position  ==2){
            return "Three"
        }
        else return "Four"
    }

}