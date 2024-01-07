package com.example.examen_ib.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examen_ib.model.Cocinero
import java.util.Date

class CocineroRepository (
    contexto: Context?, //This
): SQLiteOpenHelper(
    contexto,
    "cocinero_comida", //nombre BDD
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaCocinero =
            """
                CREATE TABLE COCINERO(
                codigoUnico VARCHAR(50) PRIMARY KEY,
                nombre VARCHAR(50),
                apellido VARCHAR(50),
                edad INTEGER,
                fechaContratacion TEXT,
                salario DOUBLE,
                isMainChef BOOLEAN
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaCocinero)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun crearCocinero(newChef: Cocinero): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()

        valoresAGuardar.put("codigoUnico", newChef.codigoUnico)
        valoresAGuardar.put("nombre", newChef.nombre)
        valoresAGuardar.put("apellido", newChef.apellido)
        valoresAGuardar.put("edad", newChef.edad)
        valoresAGuardar.put("fechaContratacion", newChef.fechaContratacion)
        valoresAGuardar.put("salario", newChef.salario)
        valoresAGuardar.put("isMainChef", newChef.isMainChef)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "COCINERO", //nombre de la tabla
                null,
                valoresAGuardar //valores
            )

        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun obtenerCocineros(): ArrayList<Cocinero> {
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura = """
            SELECT * FROM COCINERO
        """.trimIndent()

        val resultadoConsulta = baseDatosLectura.rawQuery(scriptConsultaLectura, null)



        val cocineros = arrayListOf<Cocinero>()


        if(resultadoConsulta != null && resultadoConsulta.moveToFirst()){


            do{
                val codigoUnico = resultadoConsulta.getString(0) //Indice 0
                val nombre = resultadoConsulta.getString(1)
                val apellido = resultadoConsulta.getString(2)
                val edad = resultadoConsulta.getInt(3)
                val fechaContratacion = resultadoConsulta.getString(4)
                val salario = resultadoConsulta.getDouble(5)
                val isMainChef = resultadoConsulta.getString(6)

                if(codigoUnico != null){
                    val usuarioEncontrado = Cocinero("C-001", "Carla", "Escobar", 0, "1900-01-01", 0.0, false)
                    usuarioEncontrado.codigoUnico = codigoUnico
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.apellido = apellido
                    usuarioEncontrado.edad = edad
                    usuarioEncontrado.fechaContratacion = fechaContratacion
                    usuarioEncontrado.salario = salario
                    usuarioEncontrado.isMainChef = isMainChef.toBoolean()

                    cocineros.add(usuarioEncontrado)
                }
            } while (resultadoConsulta.moveToNext())
        }

        resultadoConsulta?.close()
        baseDatosLectura.close()
        return cocineros //arreglo
    }

    fun consultarCocineroPorCodigoUnico(codigoUnico: String): Cocinero{
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura = """
            SELECT * FROM COCINERO WHERE CODIGOUNICO = ?
        """.trimIndent()

        val parametrosConsultaLectura = arrayOf(codigoUnico)

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, //Consulta
            parametrosConsultaLectura //Parametros
        )

        val existeUsuario = resultadoConsultaLectura.moveToFirst()

        val usuarioEncontrado = Cocinero("", "", "", 0, "1900-01-01", 0.0, false)
        // val arreglo = arrayListOf<BEntrenador>()
        if(existeUsuario){
            do{
                val codigoUnico = resultadoConsultaLectura.getString(0) //Indice 0
                val nombre = resultadoConsultaLectura.getString(1)
                val apellido = resultadoConsultaLectura.getString(2)
                val edad = resultadoConsultaLectura.getInt(3)
                val fechaContratacion = resultadoConsultaLectura.getString(4)
                val salario = resultadoConsultaLectura.getDouble(5)
                val isMainChef = resultadoConsultaLectura.getString(6)

                if(codigoUnico != null){
                    // val usuarioEncontrado = BEntrenador(0, "", "")
                    usuarioEncontrado.codigoUnico = codigoUnico
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.apellido = apellido
                    usuarioEncontrado.edad = edad
                    usuarioEncontrado.fechaContratacion = fechaContratacion
                    usuarioEncontrado.salario = salario
                    usuarioEncontrado.isMainChef = isMainChef.toBoolean()
                    // arreglo.add(usuarioEncontrado)
                }
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado //arreglo
    }

}