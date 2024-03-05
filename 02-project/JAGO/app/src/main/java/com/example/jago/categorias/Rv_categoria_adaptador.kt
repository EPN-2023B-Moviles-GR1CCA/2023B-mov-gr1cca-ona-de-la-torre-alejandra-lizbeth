package com.example.jago.categorias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jago.R
import com.example.jago.models.item_rv_categoria

class Rv_categoria_adaptador(
    private val lista: ArrayList<item_rv_categoria>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<Rv_categoria_adaptador.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var iconCategory: ImageView
        var titleCategory: TextView
//        var iconTrash: ImageView

        init {
            titleCategory = view.findViewById(R.id.tv_title_category)
            iconCategory = view.findViewById(R.id.img_icon_categoria)
//            iconTrash = view.findViewById(R.id.img_icon_delete_category)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.design_rv_categoria,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    //setear los datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemActual = this.lista[position]

        holder.iconCategory.setImageDrawable(itemActual.iconCategory)

        holder.titleCategory.text = itemActual.titleCategory
        // Cambiar el color de fondo del TextView
//        holder.estado.setBackgroundColor(Color.parseColor(itemActual.colorFondo))
//        // Cambiar el color del texto del TextView
//        holder.estado.setTextColor(Color.parseColor(itemActual.colorLetra))

//        holder.iconTrash.setImageDrawable(itemActual.iconTrash)
    }

    //tamano del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }
}