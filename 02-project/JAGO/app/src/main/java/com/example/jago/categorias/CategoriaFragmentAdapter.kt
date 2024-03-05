package com.example.jago.categorias

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CategoriaFragmentAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm)  {

//    private val fragmentArrayList = ArrayList<Fragment>()

    override fun getItemCount(): Int {
        return 2 // Número total de pestañas
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> IngresosFragment() // Crea tus propios fragmentos
            1 -> GastosFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }

//    fun addFragment(fragment: Fragment){
//        fragmentArrayList.add(fragment)
//
//    }
}