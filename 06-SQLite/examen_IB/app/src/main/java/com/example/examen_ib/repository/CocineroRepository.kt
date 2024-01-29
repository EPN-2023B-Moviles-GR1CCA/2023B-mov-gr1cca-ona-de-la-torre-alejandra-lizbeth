package com.example.examen_ib.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.examen_ib.model.Cocinero
import com.example.examen_ib.model.Comida
import java.util.Date

class CocineroRepository (
    contexto: Context?, //This
): SQLiteOpenHelper(
    contexto,
    "cocinero_comida", //nombre BDD
    null,
    5
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaCocinero =
            """
                CREATE TABLE COCINERO(
                codigoUnico VARCHAR(50) PRIMARY KEY ON CONFLICT ABORT,
                nombre VARCHAR(50),
                apellido VARCHAR(50),
                edad INTEGER,
                fechaContratacion TEXT,
                salario DOUBLE,
                isMainChef INTEGER
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaCocinero)

        val scriptSQLCrearTablaComida =
            """
            CREATE TABLE COMIDA(
                identificador VARCHAR(50) PRIMARY KEY ON CONFLICT ABORT,
                nombre VARCHAR(50),
                fechaCreacion TEXT,
                esPlatoDelDia INTEGER,
                tipoCocina VARCHAR(50),
                cantidadProductos INTEGER,
                precio DOUBLE,
                codigoUnico VARCHAR(50),
                CONSTRAINT fk_cocineros
                    FOREIGN KEY (codigoUnico)
                    REFERENCES COCINERO(codigoUnico)
                    ON DELETE CASCADE
            )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaComida)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 5) {
            // Elimina las tablas si existen
            db?.execSQL("DROP TABLE IF EXISTS COMIDA")
            db?.execSQL("DROP TABLE IF EXISTS COCINERO")

            // Vuelve a crear la tabla COCINERO
            val scriptSQLCrearTablaCocinero =
                """
                CREATE TABLE COCINERO(
                codigoUnico VARCHAR(50) PRIMARY KEY ON CONFLICT ABORT,
                nombre VARCHAR(50),
                apellido VARCHAR(50),
                edad INTEGER,
                fechaContratacion TEXT,
                salario DOUBLE,
                isMainChef INTEGER
                )
                """.trimIndent()
            db?.execSQL(scriptSQLCrearTablaCocinero)

            // Vuelve a crear la tabla COMIDA con ON DELETE CASCADE
            val scriptSQLCrearTablaComida =
                """
                CREATE TABLE COMIDA(
                identificador VARCHAR(50) PRIMARY KEY ON CONFLICT ABORT,
                nombre VARCHAR(50),
                fechaCreacion TEXT,
                esPlatoDelDia INTEGER,
                tipoCocina VARCHAR(50),
                cantidadProductos INTEGER,
                precio DOUBLE,
                codigoUnico VARCHAR(50),
                CONSTRAINT fk_cocineros
                    FOREIGN KEY (codigoUnico)
                    REFERENCES COCINERO(codigoUnico)
                    ON DELETE CASCADE
                )
                """.trimIndent()
            db?.execSQL(scriptSQLCrearTablaComida)
        }
    }

    /* CRUD Cocineros */

    fun crearCocinero(newChef: Cocinero): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()

        valoresAGuardar.put("codigoUnico", newChef.codigoUnico)
        valoresAGuardar.put("nombre", newChef.nombre)
        valoresAGuardar.put("apellido", newChef.apellido)
        valoresAGuardar.put("edad", newChef.edad)
        valoresAGuardar.put("fechaContratacion", newChef.fechaContratacion)
        valoresAGuardar.put("salario", newChef.salario)
        valoresAGuardar.put("isMainChef", if(newChef.isMainChef) 1 else 0)

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
                    usuarioEncontrado.isMainChef = isMainChef.equals("1")

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
                    usuarioEncontrado.codigoUnico = codigoUnico
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.apellido = apellido
                    usuarioEncontrado.edad = edad
                    usuarioEncontrado.fechaContratacion = fechaContratacion
                    usuarioEncontrado.salario = salario
                    usuarioEncontrado.isMainChef = isMainChef.equals("1")
                }
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado //arreglo
    }

    fun actualizarCocineroPorCodigoUnico(
        datosActualizados: Cocinero
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", datosActualizados.nombre)
        valoresAActualizar.put("apellido", datosActualizados.apellido)
        valoresAActualizar.put("edad", datosActualizados.edad)
        valoresAActualizar.put("fechaContratacion", datosActualizados.fechaContratacion)
        valoresAActualizar.put("salario", datosActualizados.salario)
        valoresAActualizar.put("isMainChef", if(datosActualizados.isMainChef) 1 else 0)
        //where id = ?
        val parametrosConsultaActualizar = arrayOf(datosActualizados.codigoUnico)
        val resultadoActualizcion = conexionEscritura
            .update(
                "COCINERO", //tabla
                valoresAActualizar,
                "codigoUnico = ?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if (resultadoActualizcion == -1) false else true
    }

    fun eliminarCocineroPorCodigoUnico(codigoUnico: String): Boolean{
        val conexionEscritura = writableDatabase

        val parametrosConsultaDelete = arrayOf( codigoUnico)

        val resultadoEliminacion = conexionEscritura
            .delete(
                "COCINERO", //tabla
                "codigoUnico = ?",
                parametrosConsultaDelete
            )

        conexionEscritura.close()
        return if(resultadoEliminacion == -1) false else true
    }

    /* CRUD Comidas */

    fun crearComida(newFood: Comida): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()

        valoresAGuardar.put("identificador", newFood.identificador)
        valoresAGuardar.put("nombre", newFood.nombre)
        valoresAGuardar.put("fechaCreacion", newFood.fechaCreacion)
        valoresAGuardar.put("esPlatoDelDia", if(newFood.esPlatoDelDia) 1 else 0)
        valoresAGuardar.put("tipoCocina", newFood.tipoCocina)
        valoresAGuardar.put("cantidadProductos", newFood.cantidadProductos)
        valoresAGuardar.put("precio", newFood.precio )
        valoresAGuardar.put("codigoUnico", newFood.codigoUnicoCocinero)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "COMIDA", //nombre de la tabla
                null,
                valoresAGuardar //valores
            )

        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun obtenerComidasPorCocinero(identificadorCocinero: String): ArrayList<Comida> {
        val comidas = arrayListOf<Comida>()
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura = """
            SELECT * FROM COMIDA
            WHERE CODIGOUNICO = ?
        """.trimIndent()

        val parametrosConsultaLectura = arrayOf(identificadorCocinero)
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, //Consulta
            parametrosConsultaLectura //Parametros
        )

        if(resultadoConsultaLectura != null && resultadoConsultaLectura.moveToFirst()){

            do{
                val identificador = resultadoConsultaLectura.getString(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val fechaCreacion = resultadoConsultaLectura.getString(2)
                val esPlatoDelDia = resultadoConsultaLectura.getString(3)
                val tipoCocina = resultadoConsultaLectura.getString(4)
                val cantidadProductos = resultadoConsultaLectura.getInt(5)
                val precio = resultadoConsultaLectura.getDouble(6)
                val codigoUnicoCocinero = resultadoConsultaLectura.getString(7)

                if(identificador != null){
                    val comidaEncontrada = Comida("com-01", "Encebollado", "2024-01-01", true, "Ecuatoriana", 5, 3.5, "coc-01")
                    comidaEncontrada.identificador = identificador
                    comidaEncontrada.nombre = nombre
                    comidaEncontrada.fechaCreacion = fechaCreacion
                    comidaEncontrada.esPlatoDelDia = esPlatoDelDia.equals("1")
                    comidaEncontrada.tipoCocina = tipoCocina
                    comidaEncontrada.cantidadProductos = cantidadProductos
                    comidaEncontrada.precio = precio
                    comidaEncontrada.codigoUnicoCocinero = codigoUnicoCocinero

                    comidas.add(comidaEncontrada)
                }
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura?.close()
        baseDatosLectura.close()

        return comidas //arreglo
    }

    fun consultarComidaPorIdentificadorYCocinero(identificador: String, codigoUnicoCocinero: String): Comida{
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura = """
            SELECT * FROM COMIDA WHERE IDENTIFICADOR = ? AND CODIGOUNICO = ?
        """.trimIndent()

        val parametrosConsultaLectura = arrayOf(identificador, codigoUnicoCocinero)

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, //Consulta
            parametrosConsultaLectura //Parametros
        )

        val existeComida = resultadoConsultaLectura.moveToFirst()

        val comidaEncontrada = Comida("", "", "1900-01-01", false, "", 0, 0.0, "")
        if(existeComida){
            do{
                val identificador = resultadoConsultaLectura.getString(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val fechaCreacion = resultadoConsultaLectura.getString(2)
                val esPlatoDelDia = resultadoConsultaLectura.getString(3)
                val tipoCocina = resultadoConsultaLectura.getString(4)
                val cantidadProductos = resultadoConsultaLectura.getInt(5)
                val precio = resultadoConsultaLectura.getDouble(6)
                val codigoUnicoCocinero = resultadoConsultaLectura.getString(7)

                if(identificador != null){
                    comidaEncontrada.identificador = identificador
                    comidaEncontrada.nombre = nombre
                    comidaEncontrada.fechaCreacion = fechaCreacion
                    comidaEncontrada.esPlatoDelDia = esPlatoDelDia.equals("1")
                    comidaEncontrada.tipoCocina = tipoCocina
                    comidaEncontrada.cantidadProductos = cantidadProductos
                    comidaEncontrada.precio = precio
                    comidaEncontrada.codigoUnicoCocinero = codigoUnicoCocinero
                }
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return comidaEncontrada //arreglo
    }

    fun actualizarComidaPorIdentificadorYCodigoCocinero(
        datosActualizados: Comida
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", datosActualizados.nombre)
        valoresAActualizar.put("fechaCreacion", datosActualizados.fechaCreacion)
        valoresAActualizar.put("esPlatoDelDia", if(datosActualizados.esPlatoDelDia) 1 else 0)
        valoresAActualizar.put("tipoCocina", datosActualizados.tipoCocina)
        valoresAActualizar.put("cantidadProductos", datosActualizados.cantidadProductos)
        valoresAActualizar.put("precio", datosActualizados.precio)
        //where id = ?
        val parametrosConsultaActualizar = arrayOf(datosActualizados.identificador, datosActualizados.codigoUnicoCocinero)
        val resultadoActualizcion = conexionEscritura
            .update(
                "COMIDA", //tabla
                valoresAActualizar,
                "identificador = ? and codigoUnico = ?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if (resultadoActualizcion == -1) false else true
    }

    fun eliminarComidaPorIdentificadorYCodigoCocinero(codigoComida: String, codigoUnico: String): Boolean{
        val conexionEscritura = writableDatabase

        val parametrosConsultaDelete = arrayOf( codigoComida, codigoUnico)

        val resultadoEliminacion = conexionEscritura
            .delete(
                "COMIDA", //tabla
                "identificador = ? and codigoUnico = ?",
                parametrosConsultaDelete
            )

        conexionEscritura.close()
        return if(resultadoEliminacion == -1) false else true
    }

}