package com.example.searchview_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.searchview_recyclerview.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

   private  var iceCreamList=ArrayList<iceCreamData>()
   private lateinit var adapter: IceCreamAdapter
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)

        addDataToList()

        adapter= IceCreamAdapter(iceCreamList)
        binding.recyclerView.adapter=adapter

        binding.searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query:String?) {
        if(query!=null) {
            val filterArrayList = ArrayList<iceCreamData>()
            for(i in iceCreamList){
                if(i.flavour.toLowerCase(Locale.ROOT).contains(query)){
                    filterArrayList.add(i)
                }
            }
            if(filterArrayList.isEmpty()){
                Toast.makeText(this,"No data found",Toast.LENGTH_SHORT).show()
            }else{
               adapter.setFilteredList(filterArrayList)
            }
        }

    }
    private fun addDataToList() {
        iceCreamList.add(iceCreamData("Chocolate",R.drawable.chocolate,"Chocolate ice cream is generally made by blending cocoa powder, and the eggs, cream, vanilla, and sugar used to make vanilla ice cream. Sometimes chocolate liquor is used in addition to cocoa powder or used exclusively to create the chocolate flavor.Cocoa powder gives chocolate ice cream its brown color, and it is uncommon to add other colorings"))
        iceCreamList.add(iceCreamData("ButterScotch",R.drawable.butterscotch,"Butterscotch is often used as a flavour for items such as dessert sauce, pudding, and biscuits (cookies). To that end, it can be bought in \"butterscotch chips\" made with hydrogenated (solid) fats to be similar for baking use to chocolate chips."))
        iceCreamList.add(iceCreamData("Vanilla",R.drawable.vanilla,"Vanilla is frequently used to flavor ice cream, especially in North America, Asia, and Europe.Vanilla ice cream, like other flavors of ice cream, was originally created by cooling a mixture made of cream, sugar, and vanilla above a container of ice and salt"))
        iceCreamList.add(iceCreamData("Mango",R.drawable.mango,"All you need is a blender to blend the mangoes and a beater or stand mixer to whip the cream. You do not need an ice cream maker at all. The first version of mango ice cream has condensed milk, cream and mangoes. The second version is made with only cream, sugar and mangoes"))
        iceCreamList.add(iceCreamData("Strawberry",R.drawable.strawberry,"Strawberry ice cream is a flavor of ice cream made with strawberry or strawberry flavoring. It is made by blending in fresh strawberries or strawberry flavoring with the eggs, cream, vanilla and sugar used to make ice cream. Most strawberry ice cream is colored pink or light red."))
    }
}