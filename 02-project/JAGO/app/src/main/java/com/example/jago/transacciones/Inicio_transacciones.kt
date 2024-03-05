package com.example.jago.transacciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jago.R

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.appbar.MaterialToolbar

import androidx.navigation.ui.AppBarConfiguration
import com.example.jago.categorias.Categoria_gestion
import com.example.jago.cuentas.Cuenta_gestion
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
}
