package com.example.jago

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class OlvidarContraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olvidar_contra)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setupActionBar()

        val btn_enviar = findViewById<Button>(R.id.btn_enviar)
        btn_enviar.setOnClickListener {
            enviarCorreoRecuperacion()
        }



    }

    private fun enviarCorreoRecuperacion(){
        val et_email: AppCompatEditText = findViewById(R.id.et_email_recuperar)
        val correo: String = et_email.text.toString().trim()

        if (correo.isNotEmpty()) {
            FirebaseAuth.getInstance().sendPasswordResetEmail(correo)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // El correo de restablecimiento se envió con éxito, el correo existe
                        Toast.makeText(this, "Se ha enviado un correo electrónico para restablecer tu contraseña.", Toast.LENGTH_SHORT).show()
                    } else {
                        // El proceso falló, puede ser porque el correo no existe o hay problemas de conexión
                        val errorCode = (task.exception as FirebaseAuthException).errorCode
                        if (errorCode == "ERROR_USER_NOT_FOUND") {
                            // El correo electrónico no está registrado en Firebase Authentication
                            Toast.makeText(this, "El correo electrónico no está registrado.", Toast.LENGTH_SHORT).show()
                        } else {
                            // Otro tipo de error, como problemas de red, etc.
                            Toast.makeText(this, "Error al enviar el correo electrónico de restablecimiento: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        } else {
            Toast.makeText(this, "Por favor, ingresa tu correo electrónico.", Toast.LENGTH_SHORT).show()
        }
    }


    private fun setupActionBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar_olvidar_activity)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }
        toolbar.setNavigationOnClickListener{ onBackPressed() }

    }
}