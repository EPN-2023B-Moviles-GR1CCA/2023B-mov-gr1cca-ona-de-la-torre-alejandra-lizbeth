package com.example.deber_03

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        var topAppBar = findViewById<Toolbar>(R.id.appTopBar)
        onTopAppBar(topAppBar)

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnv_menu)
        bottomNavigationView.setSelectedItemId(R.id.mi_games)
        onBottomNavigationView(bottomNavigationView)

        //Setting the data source

        val dataSource = arrayListOf<Item>()
        val vix_icon = ContextCompat.getDrawable(this, R.drawable.vix_icon)
        val vix_image = ContextCompat.getDrawable(this, R.drawable.ea_sports)
        val indrive_icon = ContextCompat.getDrawable(this, R.drawable.indriver_icon)
        val indrive_image = ContextCompat.getDrawable(this, R.drawable.fishdom)
        val deuna_icon = ContextCompat.getDrawable(this, R.drawable.deuna_icon)
        val deuna_image = ContextCompat.getDrawable(this, R.drawable.castle)
        val pedidosya_icon = ContextCompat.getDrawable(this, R.drawable.pedidosya_icon)
        val pedidosya_image = ContextCompat.getDrawable(this, R.drawable.ea_sports)

        dataSource.add(Item(vix_icon, vix_image, "Recibimos al equipo del año con más modos y tarjetas de jugadores"))
        dataSource.add(Item(indrive_icon, indrive_image, "Recibimos a los peces"))
        dataSource.add(Item(deuna_icon, deuna_image, "Recibimos al castillo"))
        inicializarRecyclerViewHorizontal(dataSource)
    }

    fun inicializarRecyclerViewHorizontal(data: ArrayList<Item>){
        var rv_horizontal = findViewById<RecyclerView>(R.id.rv_1)
        val adaptador = RecyclerViewHorizontalAdaptador(
//            this, //contexto
            data, //arreglo de datos
            rv_horizontal //Recycler view
        )

        rv_horizontal.adapter = adaptador
        rv_horizontal.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
//        rv_horizontal.layoutManager = androidx.recyclerview.widget
//            .LinearLayoutManager(this)
        rv_horizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adaptador.notifyDataSetChanged()
    }

    fun onTopAppBar(topAppBar: Toolbar){

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.app_bar_search -> {
                    // Handle favorite icon press
                    true
                }
                R.id.mi_notificaciones -> {
                    // Handle search icon press
                    true
                }
                R.id.mi_perfil -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> {false}
            }
        }
    }

    fun onBottomNavigationView(bottomNav: BottomNavigationView){

        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.mi_games -> {
                    // Handle favorite icon press
                    true
                }
                R.id.mi_apps -> {
                    // Handle search icon press
                    true
                }
                R.id.mi_book -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> {false}
            }
        }
    }
}