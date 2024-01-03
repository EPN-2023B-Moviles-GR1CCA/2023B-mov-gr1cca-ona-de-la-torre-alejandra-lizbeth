package Model

import java.util.*

class Comida (
    val identificador: String,
    var nombre: String,
    val fechaCreacion: Date,
    var esPlatoDelDia: Boolean,
    var tipoCocina: String,
    var cantidadProductos: Int,
    var precio: Double,
    val cocinero: Cocinero?
){
    init{
        if(this.cantidadProductos > 0) this.cantidadProductos else this.cantidadProductos = 3
    }

    override fun toString(): String {
        return "$nombre, esPlatoDelDia=$esPlatoDelDia, tipoCocina='$tipoCocina', precio=$precio)"
    }


}