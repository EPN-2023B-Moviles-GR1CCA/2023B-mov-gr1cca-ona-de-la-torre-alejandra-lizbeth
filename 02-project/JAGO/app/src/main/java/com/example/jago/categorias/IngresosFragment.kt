package com.example.jago.categorias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jago.R
import com.example.jago.models.item_rv_categoria

class IngresosFragment : Fragment() {

//    private var param1: String? = null
//    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ingresos, container, false)

        var rv_categoria_ingresos = view.findViewById<RecyclerView>(R.id.rv_ingresos_categoria)
        val adaptador = Rv_categoria_adaptador(
//            this, //contexto
            getDataSource(), //arreglo de datos
            rv_categoria_ingresos //Recycler view
        )

        rv_categoria_ingresos.adapter = adaptador
        rv_categoria_ingresos.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
//        rv_horizontal.layoutManager = androidx.recyclerview.widget
//            .LinearLayoutManager(this)
        rv_categoria_ingresos.layoutManager = LinearLayoutManager(requireContext())
        adaptador.notifyDataSetChanged()

        return view
    }

    fun inicializarRecyclerViewCategoria(){

    }

    fun getDataSource(): ArrayList<item_rv_categoria>{
        val item1 = ContextCompat.getDrawable(requireContext(), R.drawable.cuenta_icon)
        val item2 = ContextCompat.getDrawable(requireContext(), R.drawable.categorias_icon)
        val item3 = ContextCompat.getDrawable(requireContext(), R.drawable.date_icon)
        val arregloRvCategoria = arrayListOf<item_rv_categoria>()
        arregloRvCategoria.add(
            item_rv_categoria(item1, "Sueldo", "Ingreso")
        )
        arregloRvCategoria.add(
            item_rv_categoria(item2, "Prestamo", "Gasto")
        )
        arregloRvCategoria.add(
            item_rv_categoria(item2, "Medico", "Ingreso")
        )

        return arregloRvCategoria
    }
}