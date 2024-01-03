package Model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

class Comida (
    val identificador: String,
    var nombre: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val fechaCreacion: Date,
    var esPlatoDelDia: Boolean,
    var tipoCocina: String,
    var cantidadProductos: Int,
    var precio: Double,
    val codigoUnicoCocinero: String
){
    init{
        if(this.cantidadProductos > 0) this.cantidadProductos else this.cantidadProductos = 3
    }

    override fun toString(): String {
        return "$nombre, esPlatoDelDia=$esPlatoDelDia, tipoCocina='$tipoCocina', precio=$precio)"
    }


}