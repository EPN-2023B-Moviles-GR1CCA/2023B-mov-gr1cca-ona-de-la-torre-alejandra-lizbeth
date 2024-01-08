package com.example.examen_ib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.examen_ib.model.Cocinero
import com.google.android.material.color.utilities.SchemeFruitSalad
import com.google.android.material.snackbar.Snackbar

class Cocinero_creacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocinero_creacion)

        /*Definicion del combo box para chef principal*/
        val spinnerChefPrincipal = findViewById<Spinner>(R.id.sp_chef_principal)

        val adaptador = ArrayAdapter.createFromResource(
            this, // contexto,
            R.array.items_chef_principal,
            android.R.layout.simple_spinner_item // como se va a ver (XML).
        )
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerChefPrincipal.adapter = adaptador
        /*Fin definicion spinner*/

        /*Creacion de cocinero*/
        val btnGuardarChef = findViewById<Button>(R.id.btn_guardar_chef)
        btnGuardarChef
            .setOnClickListener {
                try {
                    val codigoUnico = findViewById<EditText>(R.id.txt_codigoUnico)
                    val nombre = findViewById<EditText>(R.id.txt_nombre)
                    val apellido = findViewById<EditText>(R.id.txt_apellido)
                    val edad = findViewById<EditText>(R.id.txt_edad)
                    val fechaContratacion = findViewById<EditText>(R.id.txt_fecha)
                    val salario = findViewById<EditText>(R.id.txt_salario)
                    val isMainChef = spinnerChefPrincipal.selectedItem.toString()

                    // Limpiar errores anteriores
                    codigoUnico.error = null
                    nombre.error = null
                    apellido.error = null
                    edad.error = null
                    fechaContratacion.error = null
                    salario.error = null

//                    mostrarSnackbar(codigoUnico.text.toString())
                    if(validarCampos(codigoUnico, nombre, apellido, edad, fechaContratacion, salario)){

                        val newChef = Cocinero(
                            codigoUnico.text.toString(),
                            nombre.text.toString(),
                            apellido.text.toString(),
                            edad.text.toString().toInt(),
                            fechaContratacion.text.toString(),
                            salario.text.toString().toDouble(),
                            isMainChef == "Si"
                        )
                        val respuesta = BDD
                            .tablaCocinero!!.crearCocinero(newChef)

                        if(respuesta) {
                            mostrarSnackbar("El cocinero se ha creado exitosamente")
                        }else{
                            mostrarSnackbar("Hubo un problema en la creacion del cocinero")
                        }
                    }

                } catch (e: Exception) {
                    Log.e("Error", "Error en la aplicación", e)
                }
            }
    }

    fun mostrarSnackbar(texto:String){
        Snackbar
            .make(
                findViewById(R.id.layout_creacion_cocinero), //view
                texto, //texto
                Snackbar.LENGTH_LONG //tiwmpo
            )
            .show()
    }

    fun validarCampos(codigoUnico: EditText, nombre: EditText, apellido: EditText, edad: EditText, fecha: EditText, salario: EditText): Boolean{
        if (codigoUnico.text.isBlank()) {
            codigoUnico.error = "Campo requerido"
            return false
        }

        if (nombre.text.isBlank()) {
            nombre.error = "Campo requerido"
            return false
        }

        if(apellido.text.isBlank()){
            apellido.error = "Campo requerido"
            return false
        }

        if (edad.text.isBlank()) {
            edad.error = "Campo requerido"
            return false
        } else {
            val edadInt = edad.text.toString().toInt()
            if (edadInt < 18) {
                edad.error = "La edad debe ser un número mayor o igual a 18"
                return false
            }
        }

        if (fecha.text.isBlank()) {
            fecha.error = "Campo requerido"
            return false
        } else {
            val fechaRegex = Regex("""^\d{4}-\d{2}-\d{2}$""")
            if (!fechaRegex.matches(fecha.text.toString())) {
                fecha.error = "Formato de fecha inválido (debe ser yyyy-MM-dd)"
                return false
            }
        }

        if(salario.text.isBlank()){
            salario.error = "Campo requerido"
            return false
        }else{
            val salarioDouble = salario.text.toString().toDouble()
            if (salarioDouble <=0) {
                salario.error = "El salario debe ser un número mayor a 0"
                return false
            }
        }
        return true
    }
}