package com.example.gr1accalod2023b

class BBaseDatosMemoria {
    //companion object

    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()

        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Alejandra", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Lizbeth", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Carolina", "c@c.com")
                )
        }
    }
}