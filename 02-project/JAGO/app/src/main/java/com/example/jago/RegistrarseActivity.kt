package com.example.jago

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import com.example.jago.firebase.FireStore
import com.example.jago.models.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegistrarseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setupActionBar()
    }

    fun usuarioRegistradoEx(){
        Toast.makeText(this, "Ha sido registrado con éxito", Toast.LENGTH_LONG).show()
        FirebaseAuth.getInstance().signOut()
        finish()
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

        val btn_registrarse = findViewById<Button>(R.id.btn_enviar)
        btn_registrarse.setOnClickListener {
            registrarUsuario()
        }

    }

    private fun registrarUsuario(){
        val et_name: AppCompatEditText = findViewById(R.id.et_name)
        val et_lastName: AppCompatEditText = findViewById(R.id.et_lastName)
        val et_cellphone: AppCompatEditText = findViewById(R.id.et_cellphone)
        val et_email: AppCompatEditText = findViewById(R.id.et_email_signin)
        val et_password: AppCompatEditText = findViewById(R.id.et_password_signin)

        val nombres: String = et_name.text.toString().trim()
        val apellidos: String = et_lastName.text.toString().trim()
        val telefono: String = et_cellphone.text.toString().trim()
        val correo: String = et_email.text.toString().trim { it <= ' ' }
        val contrasenia: String = et_password.text.toString().trim()

        if(validateForm(nombres,apellidos,telefono,correo,contrasenia)){
            mostrarSnackbar("Espere por favor")
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(correo, contrasenia)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val usuarioFire: FirebaseUser = task.result!!.user!!
                        val correoRegistrado = usuarioFire.email!!
                        val usuario = Usuario(usuarioFire.uid, nombres,apellidos, telefono.toLong(), correoRegistrado)
                        FireStore().usuarioRegistrado(this,usuario)
                    } else {
                        Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }

    private fun validateForm(nombres: String, apellidos: String, telefono: String, correoElectronico: String, contrasenia: String ) : Boolean{
        return when{
            TextUtils.isEmpty(nombres)->{
                mostrarSnackbar("Por favor ingrese sus nombres")
                false
            }
            TextUtils.isEmpty(apellidos)->{
                mostrarSnackbar("Por favor ingrese sus apellidos")
                false
            }
            TextUtils.isEmpty(telefono)->{
                mostrarSnackbar("Por favor ingrese su número de teléfono")
                false
            }
            TextUtils.isEmpty(correoElectronico)->{
                mostrarSnackbar("Por favor ingrese su correo electrónico")
                false
            }
            TextUtils.isEmpty(contrasenia)->{
                mostrarSnackbar("Por favor ingrese su contraseña")
                false
            }

            else -> {
                true
            }
        }
    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent (this, clase)
        startActivity(intent)
    }
    fun mostrarSnackbar(texto:String){
        Snackbar
            .make(
                findViewById(R.id.ly_olvidarContraseña), //view
                texto, //texto
                Snackbar.LENGTH_LONG //tiempo
            )
            .show()
    }
}