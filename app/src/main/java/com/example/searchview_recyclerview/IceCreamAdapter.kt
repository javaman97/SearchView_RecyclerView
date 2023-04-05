package com.example.searchview_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IceCreamAdapter(var iceCreamList:List<iceCreamData>):RecyclerView.Adapter<IceCreamAdapter.IceCreamViewHolder>() {

    inner class IceCreamViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val iceCreamImage:ImageView=itemView.findViewById(R.id.img_iceCream)
        val iceCreamFlavour:TextView=itemView.findViewById(R.id.txt_iceCreamName)
    }

    fun setFilteredList(iceCreamList: List<iceCreamData>){
        this.iceCreamList=iceCreamList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IceCreamViewHolder {
     val view=LayoutInflater.from(parent.context).inflate(R.layout.each_item,parent,false)
        return IceCreamViewHolder(view)
    }

    override fun getItemCount(): Int {
       return iceCreamList.size
    }

    override fun onBindViewHolder(holder: IceCreamViewHolder, position: Int) {
        holder.iceCreamImage.setImageResource(iceCreamList[position].flavourImage)
        holder.iceCreamFlavour.text= iceCreamList[position].flavour
    }
}