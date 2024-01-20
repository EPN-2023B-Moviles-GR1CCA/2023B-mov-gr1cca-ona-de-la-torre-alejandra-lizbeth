package com.example.gr1accalod2023b

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreDescripcion(
    private val contexto: FRecyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<
        FRecyclerViewAdaptadorNombreDescripcion.MyViewHolder
    >() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nombreTextView: TextView
        val descripcionTextView: TextView
        val likesTextView: TextView
        val accionButon: Button
        var numeroLikes = 0

        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            descripcionTextView = view.findViewById(R.id.tv_descripcion)
            likesTextView = view.findViewById(R.id.tv_likes)
            accionButon = view.findViewById<Button>(R.id.btn_dar_like)
            accionButon.setOnClickListener { anadirLike() }
        }

        fun anadirLike(){
            numeroLikes = numeroLikes + 1
            likesTextView.text = numeroLikes.toString()
            contexto.aumentarTotalLikes()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    //setear los datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenadorActual = this.lista[position]
        holder.nombreTextView.text = entrenadorActual.nombre
        holder.descripcionTextView.text = entrenadorActual.descripcion
        holder.likesTextView.text = "0"
        holder.accionButon.text = "ID: ${entrenadorActual.id} " + "Nombre: ${entrenadorActual.nombre}"
    }

    //tamano del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }

}