package com.example.talha.dynamictabs

import android.content.Context
import android.content.Intent
import android.text.method.Touch.onTouchEvent
import android.view.MotionEvent
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.support.v4.view.ViewCompat.setAlpha
import android.support.v4.view.ViewCompat.setTranslationY
import android.support.v4.view.ViewCompat.setTranslationX
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.talha.dynamictabs.activity.DetailActivity
import com.example.talha.dynamictabs.activity.MainScreenActivity


class VerticalViewpager : ViewPager {

    private var downX: Float = 0.toFloat()
    private var downY: Float = 0.toFloat()
    private var isTouchCaptured: Boolean = false
    var upX1: Float = 0.toFloat()
    var upY1: Float = 0.toFloat()
    var upX2: Float = 0.toFloat()
    var upY2: Float = 0.toFloat()
    val min_distance = 20
    var eventSent = false
    internal var mSwiperListener: SwipeListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        // The majority of the magic happens here
        setPageTransformer(true, VerticalPageTransformer())
        // The easiest way to get rid of the overscroll drawing that happens on the left and right
        overScrollMode = View.OVER_SCROLL_NEVER
        mSwiperListener=this.context as SwipeListener
    }
    interface SwipeListener {

        fun leftSwipe(){

        }
    }

    private inner class VerticalPageTransformer : ViewPager.PageTransformer {

       override fun transformPage(view: View, position: Float) {

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f)

            } else if (position <= 1) { // [-1,1]
                view.setAlpha(1f)

                // Counteract the default slide transition
                view.setTranslationX(view.getWidth() * -position)

                //set Y position to swipe in from top
                val yPosition = position * view.getHeight()
                view.setTranslationY(yPosition)

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f)
            }
        }
    }

    /**
     * Swaps the X and Y coordinates of your touch event.
     */
    private fun swapXY(ev: MotionEvent): MotionEvent {
        val width = width.toFloat()
        val height = height.toFloat()

        val newX = ev.y / height * width
        val newY = ev.x / width * height

        ev.setLocation(newX, newY)

        return ev
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {

        when (ev.getAction()) {
            MotionEvent.ACTION_DOWN->{
                upX1 = ev.x
                upY1 = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                upX2 = ev.getX()
                upY2 = ev.y
                if (upX1>upX2){
//                    mSwiperListener = object :SwipeListener{
//                        override fun leftSwipe() {
//
//                        }
//                    }
                    mSwiperListener?.leftSwipe()
                    Log.d("swipe","swipe from right to left")
                    var i=Intent(context,MainScreenActivity::class.java)
                    context.startActivity(i)

                }

            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
              //  isTouchCaptured = false
               // eventSent = false
            }
        }
//
//        return super.onTouchEvent(swapXY(ev))

        val intercepted = super.onInterceptTouchEvent(swapXY(ev))
        swapXY(ev) // return touch coordinates to original reference frame for any child views
        return intercepted
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return super.onTouchEvent(swapXY(ev))
    }





    companion object {

        private val Min_Scale = 0.65f
    }








}