package com.example.jago.icon

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jago.R
import com.example.jago.models.item_rv_icons

class IconSelectionDialog(context: Context, private val iconClickListener: (Int) -> Unit) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_icon_selection)

        val rv_icon_selector = findViewById<RecyclerView>(R.id.iconRecyclerView)
        // Configura el GridLayoutManager con 4 columnas
        val gridLayoutManager = GridLayoutManager(context, 4)
        val adaptador = Rv_icon_adapter(
//            this, //contexto
            getDataSource(), //arreglo de datos
            rv_icon_selector //Recycler view
        )

        rv_icon_selector.adapter = adaptador
        rv_icon_selector.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        rv_icon_selector.layoutManager = gridLayoutManager
        adaptador.notifyDataSetChanged()
    }

    fun getDataSource(): ArrayList<item_rv_icons>{
        val icon1 = ContextCompat.getDrawable(context, R.drawable.cash_icon)
        val icon2 = ContextCompat.getDrawable(context, R.drawable.dental_icon)
        val icon3 = ContextCompat.getDrawable(context, R.drawable.transporte_icon)
        val arregloRvIcons = arrayListOf<item_rv_icons>()
        arregloRvIcons.add(
            item_rv_icons(icon1, "#344545", "ic_dinero")
        )
        arregloRvIcons.add(
            item_rv_icons(icon2, "#454545", "ic_dental")
        )
        arregloRvIcons.add(
            item_rv_icons(icon3, "#465238", "ic_transporte")
        )
        arregloRvIcons.add(
            item_rv_icons(icon1, "#344545", "ic_dinero")
        )
        arregloRvIcons.add(
            item_rv_icons(icon2, "#454545", "ic_dental")
        )
        arregloRvIcons.add(
            item_rv_icons(icon3, "#465238", "ic_transporte")
        )
        arregloRvIcons.add(
            item_rv_icons(icon1, "#344545", "ic_dinero")
        )
        arregloRvIcons.add(
            item_rv_icons(icon2, "#454545", "ic_dental")
        )
        arregloRvIcons.add(
            item_rv_icons(icon3, "#465238", "ic_transporte")
        )

        return arregloRvIcons
    }
}