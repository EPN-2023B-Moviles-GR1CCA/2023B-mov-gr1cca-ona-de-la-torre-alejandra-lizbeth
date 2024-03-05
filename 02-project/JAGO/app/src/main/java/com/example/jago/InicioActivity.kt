package com.example.jago

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Button

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val btn_sign_in = findViewById<Button>(R.id.btn_sign_in_intro)
        btn_sign_in.setOnClickListener {
            irActividad(IniciarSesionActivity::class.java)
        }

        val btn_sign_up = findViewById<Button>(R.id.btn_sign_up_intro)
        btn_sign_up.setOnClickListener {
            irActividad(RegistrarseActivity::class.java)
        }

    }
    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}