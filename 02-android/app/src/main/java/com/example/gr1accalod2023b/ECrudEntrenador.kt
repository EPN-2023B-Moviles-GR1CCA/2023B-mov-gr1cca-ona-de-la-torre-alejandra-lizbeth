package com.example.gr1accalod2023b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class ECrudEntrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_entrenador)

        //Logica buscar entrenador
        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD
            .setOnClickListener{
                //Obtener componentes visuales
                val id = findViewById<EditText>(R.id.input_id)
                val nombre = findViewById<EditText>(R.id.input_nombre)
                val descripcion = findViewById<EditText>(R.id.input_descripcion)

                //Busqueda en la BDD Sqlite
                val entrenador = EBaseDeDatos.tablaEntrenador!!
                    .consultarEntrenadorPorID(
                        id.text.toString().toInt()
                    )

                //Setear los valores en los campos visuales
                if(entrenador.id == 0){
                    mostrarSnackbar("Usuario no encontrado")
                }
                id.setText(entrenador.id.toString())
                nombre.setText(entrenador.nombre)
                descripcion.setText(entrenador.descripcion)

                mostrarSnackbar("Usuario encontrado")
            }

        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd)
        botonCrearBDD
            .setOnClickListener {
                val nombre = findViewById<EditText>(R.id.input_nombre)
                val descripcion = findViewById<EditText>(R.id.input_descripcion)
                val respuesta = EBaseDeDatos
                    .tablaEntrenador!!.crearEntrenador(
                        nombre.text.toString(),
                        descripcion.text.toString()
                    )
                if (respuesta) mostrarSnackbar("Entrenador creado")
            }

        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBDD
            .setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id)
                val nombre = findViewById<EditText>(R.id.input_nombre)
                val descripcion = findViewById<EditText>(R.id.input_descripcion)
                val respuesta = EBaseDeDatos.tablaEntrenador!!
                    .actualizarEntrenadorFormulario(
                        nombre.text.toString(),
                        descripcion.text.toString(),
                        id.text.toString().toInt()
                    )
                if (respuesta) mostrarSnackbar("Usuario actualizado")
            }

        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
        botonEliminarBDD
            .setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id)
                val respuesta = EBaseDeDatos.tablaEntrenador!!
                    .eliminarEntrenadorFormulario(
                        id.text.toString().toInt()
                    )
                if (respuesta) mostrarSnackbar("Usuario eliminado")
            }
    }//Fin onCreate

    fun mostrarSnackbar(texto:String){
        Snackbar
            .make(
                findViewById(R.id.cl_sqlite), //view
                texto, //texto
                Snackbar.LENGTH_LONG //tiempo
            )
            .show()
    }
}