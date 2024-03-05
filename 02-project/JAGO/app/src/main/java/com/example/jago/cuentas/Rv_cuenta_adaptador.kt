package com.example.jago.cuentas


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jago.R
import com.example.jago.models.item_rv_cuenta

class Rv_cuenta_adaptador (
    private val lista: ArrayList<item_rv_cuenta>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<Rv_cuenta_adaptador.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var iconCuenta: ImageView
        var tituloCuenta: TextView
        var descripcionCuenta: TextView
        var saldoCuenta: TextView

        init {
            tituloCuenta = view.findViewById(R.id.tv_title_cuenta_item)
            iconCuenta = view.findViewById(R.id.img_ic_cuenta_item)
            descripcionCuenta = view.findViewById(R.id.tv_descripcion_item_cuenta)
            saldoCuenta = view.findViewById(R.id.tv_sd_total_cuenta_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.design_rv_cuenta,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    //setear los datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemActual = this.lista[position]

        holder.iconCuenta.setImageDrawable(itemActual.iconCuenta)
        holder.tituloCuenta.text = itemActual.tituloCuenta
        holder.descripcionCuenta.text = itemActual.descripcionCuenta
        holder.saldoCuenta.text = "$ " + itemActual.saldoCuenta.toString()
    }

    //tamano del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }
}