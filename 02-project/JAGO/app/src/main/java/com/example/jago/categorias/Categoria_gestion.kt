package com.example.jago.categorias

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.jago.transacciones.Inicio_transacciones
import com.example.jago.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Categoria_gestion : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria_gestion)

        var topAppBar = findViewById<MaterialToolbar>(R.id.toolbar_categoria)
        onTopAppBar(topAppBar)

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_button_categoria)
        bottomNavigationView.setSelectedItemId(R.id.navigation_categories)
        onBottomNavigationView(bottomNavigationView)


        val viewPager: ViewPager2 = findViewById(R.id.viewPagerCategoria)
        val tabLayout: TabLayout = findViewById(R.id.tabs_categoria)

        val adapterCategoria = CategoriaFragmentAdapter(this)
//        adapterCategoria.addFragment(IngresosFragment())
//        adapterCategoria.addFragment(GastosFragment())
        viewPager.adapter = adapterCategoria
        // Configurar TabLayout con ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Ingresos"
                1 -> tab.text = "Gastos"
            }
        }.attach()


        val fab = findViewById<FloatingActionButton>(R.id.fab_categoria)
        fab
            .setOnClickListener {
                val intent = Intent(this, Categoria_creacion::class.java);
                startActivity(intent);
            }
//        binding = ActivityCategoriaGestionBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_categoria_gestion)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_categories, R.id.navigation_informes
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }

    fun onTopAppBar(topAppBar: MaterialToolbar){

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.mi_settings -> {
                    // Handle favorite icon press
                    true
                }
                else -> {false}
            }
        }
    }

    fun onBottomNavigationView(bottomNav: BottomNavigationView){

        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, Inicio_transacciones::class.java);
                    startActivity(intent);
                    true
                }
                R.id.navigation_informes -> {
                    // Handle search icon press
                    true
                }
                R.id.navigation_categories -> {
                    val intent = Intent(this, Categoria_gestion::class.java);
                    startActivity(intent);
                    true
                }
                else -> {false}
            }
        }
    }
}