package Repository

import Model.Comida

class ComidaRepository {

    private val comidas: MutableList<Comida> = mutableListOf()

    fun create(comida: Comida): Comida {
        println("Creando comida")
        comidas.add(comida)
        return comida
    }

    fun getComidas(): List<Comida> {
        println("Obteniendo comidas")
        return comidas.toList()
    }

    fun getComidaByNombre(nombre: String): List<Comida> {
        println("Obteniendo comida por nombre")
        return comidas.filter { it.nombre == nombre }
    }

    fun getComidaByIdentificador(identificador: String): Comida? {
        println("obteniendo comida por identificador")
        return comidas.find { it.identificador == identificador }
    }

    fun updateComidaByIdentificador(identificador: String, nuevaComida: Comida): Comida? {
        println("Actualizando datos de comida")
        val index = comidas.indexOfFirst { it.identificador == identificador }
        if (index != -1) {
            comidas[index] = nuevaComida
            return comidas[index]
        }
        return null
    }

    fun deleteComidaByIdentificador(identificador: String) {
        println("Borrando comida")
        comidas.removeIf { it.identificador == identificador }
    }
}