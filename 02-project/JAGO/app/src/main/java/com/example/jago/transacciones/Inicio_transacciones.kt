package com.example.jago.transacciones

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jago.R

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.appbar.MaterialToolbar

import androidx.navigation.ui.AppBarConfiguration
import com.example.jago.categorias.Categoria_gestion
import com.example.jago.cuentas.Cuenta_gestion
import com.example.jago.firebase.FireStore
import com.example.jago.models.Gasto
import com.example.jago.models.Ingreso
//import com.example.jago.categorias.Categoria_gestion
//import com.example.jago.cuentas.Cuenta_gestion
//import com.example.jago.databinding.ActivityInicioTransaccionesBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Inicio_transacciones : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    //private lateinit var binding: ActivityInicioTransaccionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_transacciones)

        var topAppBar = findViewById<MaterialToolbar>(R.id.toolbar)
        onTopAppBar(topAppBar)

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_button)
        bottomNavigationView.setSelectedItemId(R.id.navigation_home)
        onBottomNavigationView(bottomNavigationView)
//        setSupportActionBar(findViewById(R.id.toolbar))

        val btnOcultarSaldo = findViewById<ImageView>(R.id.invisible_icon)
        val saldoVisible = findViewById<TextView>(R.id.tv_saldo_visible)
        val btnMostrarSaldo = findViewById<ImageView>(R.id.visible_icon)
        val saldoOculto = findViewById<TextView>(R.id.tv_saldo_invisible)
        btnOcultarSaldo
            .setOnClickListener {
                /* al dar clic en boton para ocultar saldo
                * se muestra el saldo oculto y el boton para mostrar saldo*/
                saldoOculto.visibility = View.VISIBLE
                btnMostrarSaldo.visibility = View.VISIBLE
                /*Se desactiva boton de ocultar y se quita el saldo visible*/
                    saldoVisible.visibility = View.INVISIBLE
                btnOcultarSaldo.visibility = View.INVISIBLE
            }
        btnMostrarSaldo
            .setOnClickListener {
                /* al dar clic en boton para mostrar saldo
                * se muestra el saldo y el boton para ocultarlo*/
                saldoVisible.visibility = View.VISIBLE
                btnOcultarSaldo.visibility = View.VISIBLE
                /*Se desactiva boton de mostrar y se quita el saldo oculto*/
                    saldoOculto.visibility = View.INVISIBLE
                btnMostrarSaldo.visibility = View.INVISIBLE
            }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab
            .setOnClickListener {
                val intent = Intent(this, Accion_transaccion::class.java);
                startActivity(intent);
            }

        val btnGestionarCuentas = findViewById<Button>(R.id.btn_gestionar_cuentas)
        btnGestionarCuentas
            .setOnClickListener {
                val intent = Intent(this, Cuenta_gestion::class.java);
                startActivity(intent);
            }
        obtenerSaldoTotal()
    }

    fun onTopAppBar(topAppBar: MaterialToolbar){

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.mi_settings -> {
                    // Handle favorite icon press
                    true
                }
                else -> {false}
            }
        }
    }

    fun onBottomNavigationView(bottomNav: BottomNavigationView){

        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, Inicio_transacciones::class.java);
                    startActivity(intent);
                    true
                }
                R.id.navigation_informes -> {
                    // Handle search icon press
                    true
                }
                R.id.navigation_categories -> {
                    val intent = Intent(this, Categoria_gestion::class.java);
                    startActivity(intent);
                    true
                }
                else -> {false}
            }
            }
        }

    fun obtenerIngresos(callback: (Double) -> Unit) {
        var saldoTotalIngresos = 0.0
        FireStore().obtenerTodosLosIngresos().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents) {
                val ingreso = document.toObject(Ingreso::class.java)
                if (ingreso != null) {
                    saldoTotalIngresos += ingreso.monto
                    findViewById<TextView>(R.id.tv_saldo_ingresos).text = "$" + saldoTotalIngresos.toString()
                }
            }
            callback(saldoTotalIngresos)
        }.addOnFailureListener { exception ->
            Log.e(TAG, "Error al obtener los ingresos: $exception")
            callback(0.0) // Devolver 0.0 en caso de error
        }
    }

    fun obtenerGastos(callback: (Double) -> Unit) {
        var saldoTotalGastos = 0.0
        FireStore().obtenerTodosLosGastos().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents) {
                val gasto = document.toObject(Gasto::class.java)
                if (gasto != null) {
                    saldoTotalGastos += gasto.monto
                    findViewById<TextView>(R.id.tv_saldo_gastos).text = "$" + saldoTotalGastos.toString()
                }
            }
            callback(saldoTotalGastos)
        }.addOnFailureListener { exception ->
            Log.e(TAG, "Error al obtener los gastos: $exception")
            callback(0.0) // Devolver 0.0 en caso de error
        }
    }

    fun obtenerSaldoTotal() {
        obtenerIngresos { saldoIngresos ->
            obtenerGastos { saldoGastos ->
                val saldoTotal = saldoIngresos - saldoGastos
                findViewById<TextView>(R.id.tv_saldo_visible).text = "$$saldoTotal"
            }
        }
    }
}
