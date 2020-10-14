package com.example.week02.model

import android.view.View

class View {
    companion object{
        fun View.visible() {
            this.visibility = View.VISIBLE
        }
        fun View.invisible() {
            this.visibility = View.INVISIBLE
        }
    }
}