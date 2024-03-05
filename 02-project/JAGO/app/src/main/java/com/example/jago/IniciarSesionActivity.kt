package com.example.jago

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class IniciarSesionActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        auth = FirebaseAuth.getInstance()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setupActionBar()

        val tvRegistrate = findViewById<TextView>(R.id.tv_registrate)
        tvRegistrate.setOnClickListener {
            irActividad(RegistrarseActivity::class.java)
        }

        val btn_iniciar_sesion = findViewById<TextView>(R.id.btn_sign_in)
        btn_iniciar_sesion.setOnClickListener {
            iniciarSesionUsarioRegistrado()
        }

        val tv_olvidasteContraseña = findViewById<TextView>(R.id.tv_olvidasteContraseña)
        tv_olvidasteContraseña.setOnClickListener {
            irActividad(OlvidarContraActivity::class.java)
        }
    }

    private fun iniciarSesionUsarioRegistrado(){

        val et_email: AppCompatEditText = findViewById(R.id.et_email_signin)
        val et_password: AppCompatEditText = findViewById(R.id.et_password_signin)

        val correo: String = et_email.text.toString().trim { it <= ' ' }
        val contrasenia: String = et_password.text.toString().trim()

        if(validateForm(correo, contrasenia)){
            mostrarSnackbar("Espere por favor")
            auth.signInWithEmailAndPassword(correo, contrasenia)
                .addOnCompleteListener(this) {task ->
                    if(task.isSuccessful){
                        Log.d("Inicio Sesión", "Inicio de sesión exitoso")
                        val user = auth.currentUser
                        irActividad(BaseActivity::class.java)
                    }else{
                        Log.w("Inicio Sesión","Ocurrió un error al intentar iniciar sesión", task.exception)
                        Toast.makeText(baseContext, "Autenticación falló",Toast.LENGTH_SHORT).show()
                    }

                }
        }

    }

    private fun validateForm(correoElectronico: String, contrasenia: String ) : Boolean{
        return when{

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
    private fun setupActionBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar_sign_in_activity)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }
        toolbar.setNavigationOnClickListener{ onBackPressed() }
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
    fun mostrarSnackbar(texto:String){
        Snackbar
            .make(
                findViewById(R.id.ly_iniciarSesion), //view
                texto, //texto
                Snackbar.LENGTH_LONG //tiempo
            )
            .show()
    }
}
