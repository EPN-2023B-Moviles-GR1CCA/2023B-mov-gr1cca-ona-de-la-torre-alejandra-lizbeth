package com.example.jago.cuentas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.RecyclerView
import com.example.jago.R
import com.example.jago.models.item_rv_cuenta


import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Cuenta_gestion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta_gestion)

        var topAppBar = findViewById<MaterialToolbar>(R.id.toolbar_cuenta)
        onTopAppBar(topAppBar)

        val btnOcultarSaldo = findViewById<ImageView>(R.id.invisible_icon_cuentas)
        val saldoVisible = findViewById<TextView>(R.id.tv_saldo_visible_cuentas)
        val btnMostrarSaldo = findViewById<ImageView>(R.id.visible_icon_cuentas)
        val saldoOculto = findViewById<TextView>(R.id.tv_saldo_invisible_cuentas)
        btnOcultarSaldo
            .setOnClickListener {
                /* al dar clic en boton para ocultar saldo
                * se muestra el saldo oculto y el boton para mostrar saldo*/
                saldoOculto.visibility = View.VISIBLE
                btnMostrarSaldo.visibility = View.VISIBLE

                    saldoVisible.visibility = View.INVISIBLE
                btnOcultarSaldo.visibility = View.INVISIBLE
            }
        btnMostrarSaldo
            .setOnClickListener {
                /* al dar clic en boton para mostrar saldo
                * se muestra el saldo y el boton para ocultarlo*/
                saldoVisible.visibility = View.VISIBLE
                btnOcultarSaldo.visibility = View.VISIBLE

                    saldoOculto.visibility = View.INVISIBLE
                btnMostrarSaldo.visibility = View.INVISIBLE
            }
        inicializarRecyclerViewCuentas()

        val fab = findViewById<FloatingActionButton>(R.id.fab_cuenta)
        fab
            .setOnClickListener {
                 val intent = Intent(this, Cuenta_creacion::class.java);
                startActivity(intent);
            }

    }
    fun onTopAppBar(topAppBar: MaterialToolbar){

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.mi_sort_cuentas -> {
                    // Handle favorite icon press
                    true
                }
                else -> {false}
            }
        }
        topAppBar.navigationIcon = resources.getDrawable(R.drawable.back_arrow_icon2, theme)
        topAppBar.setNavigationOnClickListener{
            onBackPressed()
        }
    }

    fun inicializarRecyclerViewCuentas(){
        var rv_cuenta = findViewById<RecyclerView>(R.id.rv_cuentas)
        val adaptador = Rv_cuenta_adaptador(
//            this, //contexto
            getDataSource(), //arreglo de datos
            rv_cuenta //Recycler view
        )

        rv_cuenta.adapter = adaptador
        rv_cuenta.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        rv_cuenta.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
//        rv_cuenta.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adaptador.notifyDataSetChanged()
    }

    fun getDataSource(): ArrayList<item_rv_cuenta>{
        val item1 = ContextCompat.getDrawable(this, R.drawable.cash_icon)
        val item2 = ContextCompat.getDrawable(this, R.drawable.cuenta_icon)
        val item3 = ContextCompat.getDrawable(this, R.drawable.date_icon)
        val arregloRvCuenta = arrayListOf<item_rv_cuenta>()
        arregloRvCuenta.add(
            item_rv_cuenta(item1, "Banco", "Cuenta del banco", 65.00)
        )
        arregloRvCuenta.add(
            item_rv_cuenta(item2, "Efectivo", "Cuenta para efectivo", 0.00)
        )
        arregloRvCuenta.add(
            item_rv_cuenta(item3, "Paypal", "Cuenta de paypal", 0.00, )
        )

        return arregloRvCuenta
    }
}