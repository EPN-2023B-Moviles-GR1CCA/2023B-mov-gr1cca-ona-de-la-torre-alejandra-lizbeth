package Repository

import Model.Cocinero
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

class CocineroRepository(private val filePath: String) {

    private val cocineros: MutableList<Cocinero> = mutableListOf()
    private val objectMapper = jacksonObjectMapper()

    init {
        loadDataFromFile()
    }

    private fun loadDataFromFile() {
        try {
            val file = File(filePath)
            if (file.exists()) {
                val jsonContent = file.readText()
                cocineros.addAll(objectMapper.readValue(jsonContent))
            } else {
                // Si el archivo no existe, crea un archivo vacío
                file.createNewFile()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun saveDataToFile() {
        try {
            val jsonContent = objectMapper.writeValueAsString(cocineros)
            val file = File(filePath)
            file.writeText(jsonContent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun create(newChef: Cocinero): Cocinero {
        println("Creando cocinero")
        cocineros.add(newChef)
        saveDataToFile()
        return newChef
    }

    fun getChefs(): List<Cocinero> {
        println("Obteniendo cocineros")
        return cocineros.toList()
    }

    fun getChefByCodigoUnico(codigoUnico: String): Cocinero?{
        println("Obteniendo cocinero por id")
        return cocineros.find { it.codigoUnico == codigoUnico }
    }

    fun getChefByNameAndLastname(name: String, lastname: String): List<Cocinero> {
        return cocineros.filter { it.nombre == name && it.apellido == lastname }
    }

    fun getChefByAge(age: Int) : List<Cocinero>{
        return cocineros.filter { it.edad == age}
    }

    fun getChefBySalary(salary: Double) : List<Cocinero>{
        return cocineros.filter { it.salario == salary }
    }

    fun updateByCodigoUnico(codeCurrentChef: String, newChef: Cocinero): Cocinero? {
        println("Actualizando datos de cocinero")
        var indexOfCurrentChef = cocineros.indexOfFirst { it.codigoUnico == codeCurrentChef }
        if (indexOfCurrentChef != -1) {
            cocineros[indexOfCurrentChef] = newChef
            saveDataToFile()
            return cocineros[indexOfCurrentChef]
        }
        return null
    }

    fun deleteByCodigoUnico(uniqueCode: String) {
        println("Borrando cocinero")
        var index = cocineros.indexOfFirst { it.codigoUnico == uniqueCode }
        if (index != -1) {
            cocineros.removeAt(index)
            saveDataToFile()
        }
    }
}