package com.example.gr1accalod2023b

class BEntrenador ( //representacion del modelo
    var id: Int,
    var nombre: String?,
    var descripcion: String?
){
    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }
}