package com.example.searchview_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView:RecyclerView
    private lateinit var searchView:SearchView
    private  var iceCreamList=ArrayList<iceCreamData>()
    private lateinit var adapter: IceCreamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recyclerView)
        searchView=findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager=LinearLayoutManager(this)

        addDataToList()

        adapter= IceCreamAdapter(iceCreamList)
        recyclerView.adapter=adapter

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
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
                if(i.flavour.lowercase(Locale.ROOT).contains(query)){
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
        iceCreamList.add(iceCreamData("Chocolate",R.drawable.chocolate))
        iceCreamList.add(iceCreamData("ButterScotch",R.drawable.butterscotch))
        iceCreamList.add(iceCreamData("Vanilla",R.drawable.vanilla))
        iceCreamList.add(iceCreamData("Mango",R.drawable.mango))
        iceCreamList.add(iceCreamData("Strawberry",R.drawable.strawberry))
    }
}