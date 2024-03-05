package com.example.jago.cuentas

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.jago.R
import com.example.jago.icon.IconSelectionDialog
import com.example.jago.models.item_rv_icons

class Cuenta_creacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta_creacion)

        // Dentro de tu actividad o fragmento
        val iconSelectionDialog = IconSelectionDialog(this) { selectedIcon ->
//            val icon: item_rv_icons = selectedIcon
            // Aquí puedes manejar la lógica cuando el usuario selecciona un ícono
            // Por ejemplo, puedes guardar el ícono seleccionado en una variable
            // o actualizar la interfaz de usuario con el ícono seleccionado
            Log.d(TAG, "Icono seleccionado" + selectedIcon)
        }

        val seleccionIcono = findViewById<TextView>(R.id.tv_icono_emogi)
        // Mostrar el diálogo cuando el usuario haga clic en un botón
        seleccionIcono.setOnClickListener {
            iconSelectionDialog.show()
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_creacion_cuenta)
        btnCancelar
            .setOnClickListener {
                finish()
            }
    }
}