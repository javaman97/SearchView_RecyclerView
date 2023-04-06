package com.example.searchview_recyclerview

import android.widget.BaseExpandableListAdapter

data class iceCreamData(
    val flavour:String,
    val flavourImage:Int,
    val desc:String,
    var isExpandableDesc: Boolean=false
)
