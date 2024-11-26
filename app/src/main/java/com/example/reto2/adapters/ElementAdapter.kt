package com.example.reto2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.reto2.R
import com.example.reto2.model.Element

class ElementAdapter(private var elementList:List<Element>, var context:Context):RecyclerView.Adapter<ElementAdapter.MyHolder>() {
    class MyHolder(itemView:View):ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.textElement)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.element_model, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = elementList.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val element = elementList[position]
        holder.name.text = String.format(element.name)
    }

    public fun updateList(updatedList:List<Element>) {
        this.elementList = updatedList
        notifyDataSetChanged()
    }

}