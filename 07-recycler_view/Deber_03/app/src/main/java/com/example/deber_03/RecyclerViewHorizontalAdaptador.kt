package com.example.deber_03

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHorizontalAdaptador(
//    private val contexto: FRecyclerView,
    private val lista: ArrayList<item_recycler_1>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<
        RecyclerViewHorizontalAdaptador.MyViewHolder
        >() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var imagen: ImageView
        var titulo: TextView
        var descripcion: TextView
        var estado: TextView

        init {
            estado = view.findViewById(R.id.tv_state)
            titulo = view.findViewById(R.id.tv_title)
            descripcion = view.findViewById(R.id.tv_descripcion)
            imagen = view.findViewById(R.id.imgView_image)
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

        holder.imagen.setImageDrawable(itemActual.imagen)

        holder.estado.text = itemActual.estado
        // Cambiar el color de fondo del TextView
        holder.estado.setBackgroundColor(Color.parseColor(itemActual.colorFondo))
        // Cambiar el color del texto del TextView
        holder.estado.setTextColor(Color.parseColor(itemActual.colorLetra))

        holder.titulo.text =  itemActual.titulo
        holder.titulo.setTextColor(Color.parseColor(itemActual.colorLetra))
        holder.titulo.setBackgroundColor(Color.parseColor(itemActual.colorFondoTitulo))

        holder.descripcion.text = itemActual.descripcion
        holder.descripcion.setTextColor(Color.parseColor(itemActual.colorLetra))
        holder.descripcion.setBackgroundColor(Color.parseColor(itemActual.colorFondoTitulo))
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