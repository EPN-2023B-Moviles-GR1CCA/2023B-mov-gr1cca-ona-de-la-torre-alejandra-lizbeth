package com.example.jago.models

import java.util.Date

class Gasto(
    var monto: Double = 0.0,
    var cuenta: String = "",
    var categoria: String = "",
    var descripcion: String = "",
    var fecha: Date = Date(),
) {
}