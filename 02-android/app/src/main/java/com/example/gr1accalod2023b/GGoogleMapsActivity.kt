package com.example.gr1accalod2023b

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions

class GGoogleMapsActivity : AppCompatActivity() {

    private lateinit var mapa: GoogleMap
    var permisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ggoogle_maps)

        solicitarPermisos()

        iniciarLogicaMapa()
    }

    fun iniciarLogicaMapa(){
        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        fragmentoMapa.getMapAsync {googleMap ->
            //with(X) => if (X != null)
            with (googleMap){
                mapa = googleMap
                establecerConfiguracionMapa()
                moverQuicentro()
                anadirPolilinea()
                anadirPoligono()

                escucharListeners()
            }
        }
    }

    fun escucharListeners(){
        mapa.setOnPolygonClickListener {
            Log.i("mapa", "setOnPolygonClickListener ${it}")
            it.tag // <-ID
        }
        mapa.setOnPolylineClickListener {
            Log.i("mapa", "setOnPolylineClickListener ${it}")
            it.tag
        }
        mapa.setOnMarkerClickListener {
            Log.i("mapa", "setOnMarkerClickListener ${it}")
            it.tag
            return@setOnMarkerClickListener true
        }
        mapa.setOnCameraMoveListener {
            Log.i("mapa", "setOnCameraMoveListener")
        }
        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa", "setOnCameraMoveStartedListener ${it}")
        }
        mapa.setOnCameraIdleListener{
            Log.i("mapa", "setOnCameraIdleListener")
        }
    }
    fun anadirPolilinea(){
        with(mapa){
            val poliLineaUno = mapa
                .addPolyline(
                    PolylineOptions()
                        .clickable(true)
                        .add(
                            LatLng(-0.1759187040647396,
                                    -78.48506472421384),
                            LatLng(-0.17632468492901104,
                            -78.48265589308046),
                            LatLng(-0.17746143130181483,
                            -78.4770533307815)
                        )
                )
            poliLineaUno.tag = "Linea - 1" // <- ID
        }
    }

    fun anadirPoligono(){
        with(mapa){
            //POLIGONO
            val poligonoUno = mapa
                .addPolygon(
                    PolygonOptions()
                        .clickable(true)
                        .add(
                            LatLng(-0.1759187040647396,
                                -78.48506472421384),
                            LatLng(-0.17632468492901104,
                                -78.48265589308046),
                            LatLng(-0.17746143130181483,
                                -78.4770533307815)
                        )
                )
            poligonoUno.fillColor = -0xc771c4
            poligonoUno.tag = "poligono - 2" // <- ID
        }
    }

    fun moverQuicentro(){
        val zoom = 17f
        val quicentro = LatLng(
            -0.1763254993252021, -78.4787077270118
        )
        val titulo = "Quicentro"
        val markQuicentro = añadirMarcador(quicentro, titulo)
        markQuicentro.tag = titulo
        moverCamaraConZoom(quicentro, zoom)
    }

    fun añadirMarcador(latLng: LatLng, title: String): Marker {
        return mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )!!
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f){
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }

    fun establecerConfiguracionMapa(){
        val contexto = this.applicationContext
        with (mapa) {
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true //tenemos permisos
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true // defecto
        }
    }

    fun solicitarPermisos(){
        val contexto = this.applicationContext
        val permisoFineLocation = android.Manifest.permission.ACCESS_FINE_LOCATION
        val permisoCoarseLocation = android.Manifest.permission.ACCESS_COARSE_LOCATION

        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                contexto,
                permisoFineLocation //permiso que van a verificar que se haya otorgado
            )

        val tienePermisos = permisosFineLocation == PackageManager
            .PERMISSION_GRANTED

        if (tienePermisos){
            permisos = true
        }else{
            ActivityCompat.requestPermissions(
                this, //contexto
                arrayOf( // Arreglo de permisos
                    permisoFineLocation, permisoCoarseLocation
                ),
                1 // codigo de petición de los permisos
            )
        }
    }
}