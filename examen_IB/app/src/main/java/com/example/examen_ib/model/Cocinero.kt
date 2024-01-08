package com.example.examen_ib.model

import java.util.Date

class Cocinero(
    var codigoUnico: String,
    var nombre: String,
    var apellido: String,
    var edad: Int,
    var fechaContratacion: String,
    var salario: Double,
    var isMainChef: Boolean
) {

    override fun toString(): String {
//        return "Cocinero(codigoUnico='$codigoUnico', nombre='$nombre', apellido='$apellido', edad=$edad, fechaContratacion=$fechaContratacion, salario=$salario, isMainChef=$isMainChef)"
        return "codigo unico es: '$codigoUnico' El cocinero $nombre $apellido. fue contratado el '$fechaContratacion' con un salario de '$salario'"
    }


}