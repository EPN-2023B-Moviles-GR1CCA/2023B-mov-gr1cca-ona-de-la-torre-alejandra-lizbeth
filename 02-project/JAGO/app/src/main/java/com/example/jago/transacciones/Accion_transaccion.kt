package com.example.jago.transacciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.jago.R

class Accion_transaccion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accion_transaccion)

        // Configura un listener de clics en toda la pantalla
        findViewById<ConstraintLayout>(R.id.action_transaction)
            .setOnTouchListener{ v, event ->
                finish()
                true
            }

        val btnAgregarIngreso = findViewById<TextView>(R.id.tv_ingreso_agregar)
        btnAgregarIngreso
            .setOnClickListener {
                val intent = Intent(this, Ingreso_creacion::class.java);
                startActivity(intent)
            }

        val btnAgregarGasto = findViewById<TextView>(R.id.tv_gasto_agregar)
        btnAgregarGasto
            .setOnClickListener {
                val intent = Intent(this, Gasto_creacion::class.java);
                startActivity(intent)
            }
    }
}