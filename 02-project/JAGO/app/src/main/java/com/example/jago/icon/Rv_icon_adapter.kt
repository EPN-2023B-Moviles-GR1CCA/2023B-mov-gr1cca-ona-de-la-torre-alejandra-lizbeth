package com.example.jago.icon

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.jago.R
import com.example.jago.models.item_rv_icons

class Rv_icon_adapter(
    private val lista: ArrayList<item_rv_icons>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<Rv_icon_adapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var icon: ImageView

        init {
            icon = view.findViewById(R.id.icon_selector)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.design_rv_color_selector,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    //setear los datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemActual = this.lista[position]

        holder.icon.setImageDrawable(itemActual.icon)
//        holder.icon.setBackgroundColor(Color.parseColor(itemActual.color))
    }

    //tamano del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }
}