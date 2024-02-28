package com.example.deber_03

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        var topAppBar = findViewById<Toolbar>(R.id.appTopBar)
        onTopAppBar(topAppBar)

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnv_menu)
        bottomNavigationView.setSelectedItemId(R.id.mi_games)
        onBottomNavigationView(bottomNavigationView)

        inicializarRecyclerViewHorizontal()
        inicializarRecyclerViewVerticalHorizontal()
    }

    fun inicializarRecyclerViewHorizontal(){
        var rv_horizontal = findViewById<RecyclerView>(R.id.rv_1)
        val adaptador = RecyclerViewHorizontalAdaptador(
//            this, //contexto
            getDataSource(), //arreglo de datos
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

    fun getDataSource(): ArrayList<item_recycler_1>{
        val item1 = ContextCompat.getDrawable(this, R.drawable.i1_rv1)
        val item2 = ContextCompat.getDrawable(this, R.drawable.i2_rv1)
        val item3 = ContextCompat.getDrawable(this, R.drawable.i3_rv_1)
        val arregloRecycler1 = arrayListOf<item_recycler_1>()
        arregloRecycler1.add(
            item_recycler_1("#CDFFC107", "#000000", "#92FFC107", item1, "¡Celebremos el carnaval!", "Ofertas y novedades por la celebración", "Ocurriendo ahora")
        )
        arregloRecycler1.add(
            item_recycler_1("#97152E", "#FFFFFF", "#C197152E", item2, "Selección de juegos sin conexión", "Nuestras ofertas sin conexión", "Esenciales")
        )
        arregloRecycler1.add(
            item_recycler_1("#97152E", "#FFFFFF", "#C197152E", item3, "Encuentra tu pareja ideal con estas ardientes tramas románticas", "Juegos geniales de citas", "En temporada")
        )

        return arregloRecycler1
    }

    fun inicializarRecyclerViewVerticalHorizontal(){
        var rv = findViewById<RecyclerView>(R.id.rv_2)
        val adaptador = RecyclerViewVerticalHorizontalAdapter(
//            this, //contexto
            getDataSourceRv2(), //arreglo de datos
            rv //Recycler view
        )

        rv.adapter = adaptador
        rv.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
//        rv_horizontal.layoutManager = androidx.recyclerview.widget
//            .LinearLayoutManager(this)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adaptador.notifyDataSetChanged()
    }

    fun getDataSourceRv2(): ArrayList<item_recycler_2>{
        val item1 = ContextCompat.getDrawable(this, R.drawable.i1_rv2)
        val item2 = ContextCompat.getDrawable(this, R.drawable.i2_rv2)
        val item3 = ContextCompat.getDrawable(this, R.drawable.i3_rv2)
        val item4 = ContextCompat.getDrawable(this, R.drawable.i4_rv2)
        val item5 = ContextCompat.getDrawable(this, R.drawable.i5_rv2)
        val item6 = ContextCompat.getDrawable(this, R.drawable.i6_rv2)
        val item7 = ContextCompat.getDrawable(this, R.drawable.i7_rv2)
        val item8 = ContextCompat.getDrawable(this, R.drawable.i8_rv2)
        val item9 = ContextCompat.getDrawable(this, R.drawable.i9_rv2)
        val item10 = ContextCompat.getDrawable(this, R.drawable.i10_rv2)
        val item11 = ContextCompat.getDrawable(this, R.drawable.i11_rv2)
        val item12 = ContextCompat.getDrawable(this, R.drawable.i12_rv2)
        val item13 = ContextCompat.getDrawable(this, R.drawable.i13_rv2)
        val item14 = ContextCompat.getDrawable(this, R.drawable.i14_rv2)
        val item15 = ContextCompat.getDrawable(this, R.drawable.i15_rv2)
        val item16 = ContextCompat.getDrawable(this, R.drawable.i16_rv2)
        val item17 = ContextCompat.getDrawable(this, R.drawable.i17_rv2)
        val item18 = ContextCompat.getDrawable(this, R.drawable.i18_rv2)
        val item19 = ContextCompat.getDrawable(this, R.drawable.i19_rv2)
        val item20 = ContextCompat.getDrawable(this, R.drawable.i20_rv2)
        val item21 = ContextCompat.getDrawable(this, R.drawable.i21_rv2)
        val item22 = ContextCompat.getDrawable(this, R.drawable.i22_rv2)
        val item23 = ContextCompat.getDrawable(this, R.drawable.i23_rv2)
        val item24 = ContextCompat.getDrawable(this, R.drawable.i24_rv2)

        val arregloRecycler = arrayListOf<item_recycler_2>()
        arregloRecycler.add(
            item_recycler_2(item1, "World of Water", "Estrategia . Juegos de rol . Juegos ocasionales", 5.0, "151 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item2, "Candy Crush Saga", "Acertijos . Juegos de unir 3 elementos", 4.6, "85 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item3, "Royal Match", "Acertijos . Juegos ocasionales . Sin conexión", 4.6, "169 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item4, "Lords Mobile: ¡Pagani GO!", "Estrategia . 4X", 4.5, "414 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item5, "¡MONOPOLY GO!", "Juegos de mesa . Juegos ocasionales", 4.8, "122 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item6, "Clash Royale", "Estrategia . Táctica . Juegos ocasionales", 4.4, "452 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item7, "Sea of Conquest: Pirate War", "Estrategia", 4.7, "513 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item8, "EA SPORTS FC Mobile Futbol", "Deportes . Fútbol . Juegos ocasionales", 4.5, "116 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item9, "Evony - The King's Return", "Estrategia . 4X", 4.0, "703 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item10, "War Robots. PvP Multijugador", "Acción . Combate de maquinaria bélica", 4.5, "88 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item11, "Words of Wonders: Crucigrama", "Juegos de palabras . Sopa de letras", 4.8, "165 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item12, "Clash of Clans", "Estrategias . Juegos de construcción y combate", 4.7, "242 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item13, "Hero Wars: Alliance", "Juegos de rol . Acción y aventura", 4.8, "100 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item14, "Rise of Kingdoms: Lost Crusade", "Estrategia . 4X", 4.7, "1.0 GB")
        )
        arregloRecycler.add(
            item_recycler_2(item15, "Candy Crush Soda Saga", "Acertijos . Juegos de unir 3 elementos", 4.6, "97 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item16, "Puzzle Game", "Acertijos . Bloque . Sin conexión", 4.1, "14 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item17, "Tiledom: Puzzle de combinación", "Acertijos . Juegos de unir 3 elementos", 4.5, "37 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item18, "Onet Puzzle - Juego de enlace", "Juegos de mesa . Acertijos . Unir pares", 4.5, "137 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item19, "June's Journey - Detectives", "Aventura . Acertijos . Objetos ocultos", 4.5, "119 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item20, "Family Island: Juego de granja", "Simulación . Administración . Un solo jugador", 4.6, "562 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item21, "Juegos de cartas de solitario", "Cartas . Solitario . Juegos ocasionales", 4.8, "50 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item22, "Solitario - Juego de Cartas", "Cartas . Solitario . Juegos ocasionales", 4.8, "68 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item23, "Rush Royale: Tower Defense TD", "Estrategia . Defensa de torres", 4.3, "126 MB")
        )
        arregloRecycler.add(
            item_recycler_2(item24, "Wood Block Puzzle - Sudoku Jigsaw", "Acertijos . Bloques . Juegos ocasionales", 4.6, "136 MB")
        )

        return arregloRecycler
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