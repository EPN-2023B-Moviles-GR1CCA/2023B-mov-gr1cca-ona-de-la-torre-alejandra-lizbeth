package com.example.examen_ib

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.examen_ib.model.Cocinero
import com.example.examen_ib.repository.CocineroRepository
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    var cocineros = arrayListOf<Cocinero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        //Inicializacion de la base de datos
        BDD.tablaCocinero = CocineroRepository(this)

        cocineros = BDD.tablaCocinero!!.obtenerCocineros()

        if(cocineros.size != 0){
            //listado de cocineros
            val listView = findViewById<ListView>(R.id.lv_list_cocineros)

            val adaptador = ArrayAdapter(
                this, // contexto
                android.R.layout.simple_list_item_1, // como se va a ver (XML)
                cocineros
            )

            listView.adapter = adaptador
            adaptador.notifyDataSetChanged()
            registerForContextMenu(listView)
        }else{
            mostrarSnackbar("No existen cocineros")
        }

        //TODO por ahora la lista de cocineros esta vacia, crear la actividad para creacion de usuarios, crear un usuario y volver a probar el funcionamiento del listado

        val btnCrearCocinero = findViewById<Button>(R.id.id_btn_crear)
        btnCrearCocinero
            .setOnClickListener {
                irActividad(Cocinero_creacion::class.java)
            }


//        val botonIntentImplicito = findViewById<Button>(
//            R.id.btn_ir_intent_implicito
//        )
//        botonIntentImplicito
//            .setOnClickListener{
//                val intentConRespuesta = Intent(
//                    Intent.ACTION_PICK,
//                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
//                )
//                callbackIntentImplicitoTelefono.launch(intentConRespuesta)
//            }
//
//        val botonIntentExplicito = findViewById<Button>(
//            R.id.btn_ir_intent_explicito
//        )
//        botonIntentExplicito
//            .setOnClickListener{
//                abrirActividadConParametros(
//                    (CIntentExplicitoParametros::class.java)
//                )
//            }
//
//        val botonSqlite = findViewById<Button>(R.id.btn_sqlite)
//        botonSqlite
//            .setOnClickListener{
//                irActividad(ECrudEntrenador::class.java)
//            }
    }//termina onCreate

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun mostrarSnackbar(texto:String){
        Snackbar
            .make(
                findViewById(R.id.constraint_cocineros), //view
                texto, //texto
                Snackbar.LENGTH_LONG //tiwmpo
            )
            .show()
    }
}