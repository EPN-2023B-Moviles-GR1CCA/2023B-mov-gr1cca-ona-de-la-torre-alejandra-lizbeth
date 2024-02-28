package com.example.deber_03

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewVerticalHorizontalAdapter (
//    private val contexto: FRecyclerView,
    private val lista: ArrayList<item_recycler_2>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<
        RecyclerViewVerticalHorizontalAdapter.MyViewHolder
        >() {
    private var currentIndex = 0
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        var icono1: ImageView
        var icono2: ImageView
        var icono3: ImageView
        var titulo1: TextView
        var titulo2: TextView
        var titulo3: TextView
        var descripcion1: TextView
        var descripcion2: TextView
        var descripcion3: TextView
        var valoracion1: TextView
        var valoracion2: TextView
        var valoracion3: TextView
        var tamanio1: TextView
        var tamanio2: TextView
        var tamanio3: TextView

        init {
            icono1 = view.findViewById(R.id.icono1)
            icono2 = view.findViewById(R.id.icono2)
            icono3 = view.findViewById(R.id.icono3)
            titulo1 = view.findViewById(R.id.tv_titulo1rv2)
            titulo2 = view.findViewById(R.id.tv_titulo2rv2)
            titulo3 = view.findViewById(R.id.tv_titulo3rv2)
            descripcion1 = view.findViewById(R.id.tv_descripcion1rv2)
            descripcion2 = view.findViewById(R.id.tv_descripcion2rv2)
            descripcion3 = view.findViewById(R.id.tv_descripcion3rv2)
            valoracion1 = view.findViewById(R.id.tv_valoracion1)
            valoracion2 = view.findViewById(R.id.tv_valoracion2)
            valoracion3 = view.findViewById(R.id.tv_valoracion3)
            tamanio1 = view.findViewById(R.id.tv_tamanio1)
            tamanio2 = view.findViewById(R.id.tv_tamanio2)
            tamanio3 = view.findViewById(R.id.tv_tamanio3)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.vertical_horizontal_item,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    //setear los datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentIndex = position * 3

        if(currentIndex < lista.size){
            val item1 = lista[currentIndex]

            // Establecer los datos en los elementos de la vista para el primer conjunto
            holder.icono1.setImageDrawable(item1.icono)
            holder.titulo1.text = item1.titulo
            holder.descripcion1.text = item1.descripcion
            holder.valoracion1.text = item1.valoracion.toString()
            holder.tamanio1.text = item1.tamaño
        }

        if(currentIndex + 1 < lista.size){
            val item2 = lista[currentIndex + 1]

            // Establecer los datos en los elementos de la vista para el segundo conjunto
            holder.icono2.setImageDrawable(item2.icono)
            holder.titulo2.text = item2.titulo
            holder.descripcion2.text = item2.descripcion
            holder.valoracion2.text = item2.valoracion.toString()
            holder.tamanio2.text = item2.tamaño
        }

        if(currentIndex + 2 < lista.size){
            val item3 = lista[currentIndex + 2]

            // Establecer los datos en los elementos de la vista para el tercer conjunto
            holder.icono3.setImageDrawable(item3.icono)
            holder.titulo3.text = item3.titulo
            holder.descripcion3.text = item3.descripcion
            holder.valoracion3.text = item3.valoracion.toString()
            holder.tamanio3.text = item3.tamaño
        }
    }

    //tamano del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }
}