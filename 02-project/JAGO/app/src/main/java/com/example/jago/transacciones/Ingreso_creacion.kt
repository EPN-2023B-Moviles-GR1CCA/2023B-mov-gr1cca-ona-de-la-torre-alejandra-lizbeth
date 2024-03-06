package com.example.jago.transacciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.jago.R
import com.example.jago.firebase.FireStore
import com.example.jago.models.Ingreso
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Ingreso_creacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso_creacion)

        /*Definicion del combo box para chef principal*/
        val spinnerCuentaIngreso = findViewById<Spinner>(R.id.sp_cuenta)

        val adaptadorcuenta = ArrayAdapter.createFromResource(
            this, // contexto,
            R.array.items_cuenta,
            android.R.layout.simple_spinner_item // como se va a ver (XML).
        )
        adaptadorcuenta.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerCuentaIngreso.adapter = adaptadorcuenta
        /*Fin definicion spinner*/

        /*Definicion del combo box para chef principal*/
        val spinnerCategoriaIngreso = findViewById<Spinner>(R.id.sp_categoria)

        val adaptadorcategoria = ArrayAdapter.createFromResource(
            this, // contexto,
            R.array.items_categoria_ingresos,
            android.R.layout.simple_spinner_item // como se va a ver (XML).
        )
        adaptadorcategoria.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerCategoriaIngreso.adapter = adaptadorcategoria
        /*Fin definicion spinner*/


        // Obtener la referencia al EditText de fecha
        val fechaEditText = findViewById<EditText>(R.id.et_fecha_ingreso)

        // Agregar un TextWatcher al EditText de fecha
        fechaEditText.addTextChangedListener(object : TextWatcher {
            var isEditing = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No necesitamos hacer nada antes de cambiar el texto
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No necesitamos hacer nada mientras el texto está cambiando
            }

            override fun afterTextChanged(s: Editable?) {
                if (isEditing) {
                    return
                }

                isEditing = true

                val text = s.toString()

                if (text.length == 3 && text[2] != '/') {
                    val newText = StringBuilder(text).apply {
                        insert(2, '/')
                    }.toString()

                    fechaEditText.setText(newText)
                    fechaEditText.setSelection(newText.length)
                } else if (text.length == 6 && text[5] != '/') {
                    val newText = StringBuilder(text).apply {
                        insert(5, '/')
                    }.toString()

                    fechaEditText.setText(newText)
                    fechaEditText.setSelection(newText.length)
                }

                isEditing = false
            }
        })

        /*Creacion de cocinero*/
        val btnGuardarIngreso = findViewById<Button>(R.id.btn_guardar_ingreso)
        btnGuardarIngreso
            .setOnClickListener {
                try {

                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

//                    val codigoUnico = findViewById<EditText>(R.id.txt_codigoUnico)
                    val monto = findViewById<EditText>(R.id.monto_ingreso)
                    val cuenta = spinnerCuentaIngreso.selectedItem.toString()
                    val categoria = spinnerCategoriaIngreso.selectedItem.toString()
                    val descripcion = findViewById<EditText>(R.id.et_descripcion_ingreso)
                    val fecha = findViewById<EditText>(R.id.et_fecha_ingreso)

                    val fechaDate: Date = dateFormat.parse(fecha.text.toString())
                    // Limpiar errores anteriores
//                    codigoUnico.error = null
//                    nombre.error = null
//                    apellido.error = null
//                    edad.error = null
//                    fechaContratacion.error = null
//                    salario.error = null

//                    if(validarCampos(codigoUnico, nombre, apellido, edad, fechaContratacion, salario, isMainChef)){
//                        val esPrincipal = isMainChef.equals("Si")
                        val nuevoIngreso = Ingreso(
                            monto.text.toString().toDouble(),
                            cuenta,
                            categoria,
                            descripcion.text.toString(),
                            fechaDate
                        )

                        FireStore().agregarIngreso(nuevoIngreso)
                            .addOnSuccessListener {
                                val returnIntent = Intent(this, Inicio_transacciones::class.java)
                                returnIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                returnIntent.putExtra("monto", nuevoIngreso.monto)
                                startActivity(returnIntent)
                                finish()
                            }
                            .addOnFailureListener { e ->
                                mostrarSnackbar("Hubo un problema al agregar un ingreso")
                            }
//                    }

                } catch (e: Exception) {
                    Log.e("Error", "Error en la aplicación", e)
                }
            }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_ingreso)
        btnCancelar
            .setOnClickListener {
                val returnIntent = Intent(this, Inicio_transacciones::class.java)
                returnIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(returnIntent)
                finish()
            }
    }

    fun mostrarSnackbar(texto:String){
        Snackbar
            .make(
                findViewById(R.id.ingreso_creacion), //view
                texto, //texto
                Snackbar.LENGTH_LONG //tiwmpo
            )
            .show()
    }
}