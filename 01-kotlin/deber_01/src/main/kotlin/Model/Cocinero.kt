package Model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

class Cocinero(
    val codigoUnico: String,
    var nombre: String,
    var apellido: String,
    var edad: Int,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val fechaContratacion: Date,
    var salario: Double,
    var isMainChef: Boolean,
//    val comidaRepository: ComidaRepository
) {

    init{
        if(this.edad >= 18) this.edad
        if(this.salario > 0) this.salario
    }

//    fun prepararComida(nuevaComida: Comida) : Comida {
//        val comidaNueva = comidaRepository.create(nuevaComida)
//        println("$nombre $apellido ha preparado la comida: ${comidaNueva.nombre}")
//        return comidaNueva
//    }

//    fun getFechaContratacion(): String {
//        val formato = SimpleDateFormat("dd/MM/yyyy")
//        return formato.format(fechaContratacion)
//    }


    override fun toString(): String {
//        return "Cocinero(codigoUnico='$codigoUnico', nombre='$nombre', apellido='$apellido', edad=$edad, fechaContratacion=$fechaContratacion, salario=$salario, isMainChef=$isMainChef)"
        return "$nombre $apellido "
    }


}