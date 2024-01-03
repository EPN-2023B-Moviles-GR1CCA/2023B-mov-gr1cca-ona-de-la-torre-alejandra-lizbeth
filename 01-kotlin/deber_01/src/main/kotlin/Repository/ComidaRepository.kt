package Repository

import Model.Comida
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

class ComidaRepository(private val filePath: String) {

    private val comidas: MutableList<Comida> = mutableListOf()
    private val objectMapper = jacksonObjectMapper()

    init {
        loadDataFromFile()
    }

    private fun loadDataFromFile() {
        try {
            val file = File(filePath)
            if (file.exists()) {
                val jsonContent = file.readText()
                comidas.addAll(objectMapper.readValue(jsonContent))
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
            val jsonContent = objectMapper.writeValueAsString(comidas)
            val file = File(filePath)
            file.writeText(jsonContent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun create(comida: Comida): Comida {
        println("Creando comida")
        comidas.add(comida)
        saveDataToFile()
        return comida
    }

    fun getComidasByIdentificadorCocinero(identificadorCocinero: String): List<Comida>{
        println("Obteniendo comidas ${identificadorCocinero}")
        return comidas.filter { it.codigoUnicoCocinero == identificadorCocinero }
    }

    fun getComidas(): List<Comida> {
        println("Obteniendo comidas")
        return comidas.toList()
    }

    fun getComidaByNombre(nombre: String): List<Comida> {
        println("Obteniendo comida por nombre")
        return comidas.filter { it.nombre == nombre }
    }

    fun getComidaByIdentificadorAndCodigoChef(identificador: String, codigoChef: String): Comida? {
        println("obteniendo comida por identificador y codigo del chef")
        return comidas.find { it.identificador == identificador && it.codigoUnicoCocinero == codigoChef}
    }

    fun updateComidaByIdentificadorAndCodigoChef(identificador: String, nuevaComida: Comida, codigoChef: String): Comida? {
        println("Actualizando datos de comida")
        val index = comidas.indexOfFirst { it.identificador == identificador && it.codigoUnicoCocinero == codigoChef }
        if (index != -1) {
            comidas[index] = nuevaComida
            saveDataToFile()
            return comidas[index]
        }
        return null
    }

    fun deleteComidaByIdentificadorAndCodigoChef(identificador: String, codigoChef: String) : Boolean {
        println("Borrando comida")
        var index = comidas.indexOfFirst { it.identificador == identificador && it.codigoUnicoCocinero == codigoChef }
        if (index != -2) {
            comidas.removeAt(index)
            saveDataToFile()
            return true
        }
        return false
    }
}