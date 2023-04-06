package com.example.searchview_recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class IceCreamAdapter(private var iceCreamList:List<iceCreamData>):RecyclerView.Adapter<IceCreamAdapter.IceCreamViewHolder>() {

    inner class IceCreamViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val iceCreamImage:ImageView=itemView.findViewById(R.id.img_iceCream)
        val iceCreamFlavour:TextView=itemView.findViewById(R.id.txt_iceCreamName)
        val iceCreamDesc:TextView=itemView.findViewById(R.id.txt_iceCreamDesc)
        val constraintLayout:ConstraintLayout=itemView.findViewById(R.id.constraintLayout)

        fun collapseExpandable(){
            iceCreamDesc.visibility=View.GONE
        }
    }

    fun setFilteredList(iceCreamList: List<iceCreamData>){
        this.iceCreamList=iceCreamList
        notifyDataSetChanged()
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IceCreamViewHolder {
     val view=LayoutInflater.from(parent.context).inflate(R.layout.each_item,parent,false)
        return IceCreamViewHolder(view)
    }

    override fun getItemCount(): Int {
       return iceCreamList.size
    }

    override fun onBindViewHolder(holder: IceCreamViewHolder, position: Int) {
        val iceCreamData=iceCreamList[position]
        holder.iceCreamImage.setImageResource(iceCreamData.flavourImage)
        holder.iceCreamFlavour.text= iceCreamData.flavour
        holder.iceCreamDesc.text=iceCreamData.desc

        val isExpandable:Boolean=iceCreamData.isExpandableDesc
        holder.iceCreamDesc.visibility=if(isExpandable)View.VISIBLE else View.GONE

        holder.constraintLayout.setOnClickListener {
            isAnyItemExpanded(position)
            iceCreamData.isExpandableDesc=!iceCreamData.isExpandableDesc
            notifyItemChanged(position,Unit) // Unit is same as Void
        }
    }

    private fun isAnyItemExpanded(position: Int) {   // to check if any item is expanded

        val temp= iceCreamList.indexOfFirst {
            it.isExpandableDesc
        }
        if(temp>=0 && temp!=position){
            iceCreamList[temp].isExpandableDesc=false
            notifyItemChanged(temp,0) // payload is implemeneted when we override bindViewHolder
        }
    }

    override fun onBindViewHolder(
        holder: IceCreamViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if(payloads.isNotEmpty() && payloads[0]==0){
            holder.collapseExpandable()
        }else{
        super.onBindViewHolder(holder, position, payloads)
    }
    }

}