package com.example.gr1accalod2023b

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gr1accalod2023b.ui.theme.Gr1accalod2023bTheme
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    // Logica Negocio
                    val data = result.data
                    mostrarSnackbar(
                        "${data?.getStringExtra("nombreModificado")}"
                    )
                }
            }
        }

    fun mostrarSnackbar(texto:String){
        Snackbar
            .make(
                findViewById(R.id.id_layout_main), //view
                texto, //texto
                Snackbar.LENGTH_LONG //tiempo
            )
            .show()
    }

    val callbackIntentImplicitoTelefono =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if(result.resultCode === Activity.RESULT_OK){
                if(result.data != null){
                    if(result.data!!.data != null){
                        val uri: Uri = result.data!!.data!!
                        val cursor = contentResolver.query(
                            uri, null, null, null, null, null
                        )
                        cursor?.moveToFirst()
                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono = cursor?.getString(indiceTelefono!!)
                        cursor?.close()
                        mostrarSnackbar("Telefono ${telefono}")
                    }
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //Inicializacion de la base de datos
        EBaseDeDatos.tablaEntrenador = ESqliteHelperEntrenador(this)

        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
                irActividad(ACicloVida::class.java)
            }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener{
                irActividad(BListView::class.java)
            }

        val botonIntentImplicito = findViewById<Button>(
            R.id.btn_ir_intent_implicito
        )
        botonIntentImplicito
            .setOnClickListener{
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                callbackIntentImplicitoTelefono.launch(intentConRespuesta)
            }

        val botonIntentExplicito = findViewById<Button>(
            R.id.btn_ir_intent_explicito
        )
        botonIntentExplicito
            .setOnClickListener{
                abrirActividadConParametros(
                    (CIntentExplicitoParametros::class.java)
                )
            }

        val botonSqlite = findViewById<Button>(R.id.btn_sqlite)
        botonSqlite
            .setOnClickListener{
                irActividad(ECrudEntrenador::class.java)
            }

        val botonRView = findViewById<Button>(R.id.btn_revcycler_view)
        botonRView
            .setOnClickListener {
                irActividad(FRecyclerView::class.java)
            }

        val botonGoogleMaps = findViewById<Button>(R.id.btn_google_maps)
        botonGoogleMaps
            .setOnClickListener {
                irActividad(GGoogleMapsActivity::class.java)
            }

        val botonFirebaseUI = findViewById<Button>(R.id.btn_intent_firebase_ui)
        botonFirebaseUI
            .setOnClickListener {
                irActividad(HFirebaseUIAuth::class.java)
            }
    }//termina onCreate

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        //Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", "Alejandra")
        intentExplicito.putExtra("apellido", "Oña")
        intentExplicito.putExtra("edad", 23)
        callbackContenidoIntentExplicito.launch(intentExplicito)
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
