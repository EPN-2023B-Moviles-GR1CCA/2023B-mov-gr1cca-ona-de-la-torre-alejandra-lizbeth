package com.example.deber_03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHorizontalAdaptador(
//    private val contexto: FRecyclerView,
    private val lista: ArrayList<Item>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<
        RecyclerViewHorizontalAdaptador.MyViewHolder
        >() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val descripcion: TextView
        val imgView_image: ImageView
//        val likesTextView: TextView
//        val accionButon: Button
//        var numeroLikes = 0

        init {
            descripcion = view.findViewById(R.id.tv_descripcion)
            imgView_image = view.findViewById(R.id.imgView_image)
//            likesTextView = view.findViewById(R.id.tv_likes)
//            accionButon = view.findViewById<Button>(R.id.btn_dar_like)
//            accionButon.setOnClickListener { anadirLike() }
        }

//        fun anadirLike(){
//            numeroLikes = numeroLikes + 1
//            likesTextView.text = numeroLikes.toString()
//            contexto.aumentarTotalLikes()
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.horizontal_item,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    //setear los datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemActual = this.lista[position]
//        holder.icon.setText(itemActual.icono)
        holder.imgView_image.setImageDrawable(itemActual.imagen)
        holder.descripcion.text = itemActual.descripcion
//        val entrenadorActual = this.lista[position]
//        holder.nombreTextView.text = entrenadorActual.nombre
//        holder.descripcionTextView.text = entrenadorActual.descripcion
//        holder.likesTextView.text = "0"
//        holder.accionButon.text = "ID: ${entrenadorActual.id} " + "Nombre: ${entrenadorActual.nombre}"
    }

    //tamano del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }
}