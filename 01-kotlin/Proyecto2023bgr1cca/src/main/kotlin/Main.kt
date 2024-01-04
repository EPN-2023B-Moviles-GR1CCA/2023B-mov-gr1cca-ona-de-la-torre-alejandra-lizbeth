import java.util.*
import kotlin.collections.ArrayList

fun main() {
    println("Hola Mundo")

    //INMUTABLES (NO se reasigna "=")
    val inmutable: String = "Alejandra";
    //inmutable = "Alejandra";

    //MUTABLES (Re asignar)
    var mutable: String = "Alejandra";
    mutable = "Alejandra";

    // val > var

    //DUCK TYPING
    var ejemploVariable = " Adrian Eguez "
    val edadEjemplo: Int = 12
    ejemploVariable.trim()
    // ejemploVariable = edadEjempli0;

    //Variable primitiva
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true

    //Clases Java
    val fechaNacimiento: Date = Date()

    //Metodos de Java en Kotlin
    //switch => when
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }
    //if
    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"

    //PARAMETROS NOMBRADOS
    //Se puede definir a los parametros en cualquier orden
    calcularSueldo(sueldo = 10.00)
    calcularSueldo(sueldo = 10.00, tasa = 15.00)
    calcularSueldo(sueldo = 10.00, tasa = 12.00, bonoEspecial = 20.00)

    calcularSueldo(sueldo = 10.00, bonoEspecial = 20.00) //Named Parameters
    calcularSueldo(10.00, bonoEspecial = 20.00) //Named Parameters

    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) // Named Parameters

    //Instancia de la clase suma
    //No es necesario poner la palabra reservada "new"
    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)

    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //ARREGLOS

    //Tipos de arreglos

    //Arreglo estatico
    //no se puede cambiar el numero de elemento que existen
    val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
    println(arregloEstatico)

    //Arreglo Dinamico
    // se puede cambiar el numero de elementos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    )
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    // operadores de arreglos
    // FOR EACH ->Unit
    // Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach{
            valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }

    //it (en ingles eso) significa el elemento iterado
    // it se puede ocupar solo si se tiene un parametro
    arregloDinamico.forEach{ println(it)}

    // For Each Index: no devuelve ningun valor
    arregloEstatico
        .forEachIndexed{ indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    //MAP -> Itera el arreglo y nos devuelve un nuevo arreglo
    // con los valores que nosotros queremos modificar
    val respuestaMap: List<Double> = arregloDinamico
        .map{ valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map{ it + 15}

    // Filter -> Filtrar el arreglo
    // 1)Devolver una exoresion (true o false)
    // 2) Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            // Expresion Condicion
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR AND
    // OR -> ANY (Alguna cumple?)
    // AND -> ALL (Todos cumplen?)
    // V AND V -> V    V AND F -> F
    // V OR V -> V    V OR F -> V    F OR F -> F
    // devuelven un valor booleano
    val respuestaAny: Boolean = arregloDinamico
        .any{ valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
        .all{ valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) //false

    // REDUCE -> devuelve el Valor acumulado
    // Valor acumulado = 0 (siempre 0 en lenguaje Kotlin)
    //[1, 2, 3, 4, 5 ] -> Sumeme todo los valores del arreglo
    //Iteracion 1 = valorEmpieza + 1 = 0 + 1 = 1-> Iteracion 1
    //Iteracion 2 = valorIteracion1 + 2 = 1 + 2 = 3-> Iteracion 2
    //Iteracion 3 = valorIteracion2 + 3 = 3 + 3 = 6-> Iteracion 3
    //Iteracion 4 = valorIteracion3 + 4 = 6 + 4 = 10-> Iteracion 4
    //Iteracion 1 = valorIteracion4 + 5 = 10 + 5 = 15-> Iteracion 5

    val respuestaReduce: Int = arregloDinamico
        .reduce{ //acumulado = 0 -> siempre empieza en cero
            acumulado: Int, valorActual: Int ->
            return @reduce (acumulado + valorActual) // -> logica del negocio
        }
    println(respuestaReduce)
}

//CLASES
/**
 * En Kotlin el constructor primario se lo pone despues del nombre de la clase
 * public: es el modificador por defecto y no se lo necesita escribir para declarar la variable
 */
abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(uno: Int, dos: Int){
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros( //Constructor private

    protected val numeroUno: Int,
    protected val numeroDos: Int
){
    init{ //bloque de codigo constructor primario
        this.numeroUno; this.numeroDos; //"this" es opcional
        numeroUno; numeroDos; //Sin el "this", es lo mismo
        println("Inicializando")
    }
}

class Suma( // <- Constructor Primaro de Suma
    uno: Int,
    dos: Int
): Numeros(uno, dos){ // <- Constructor del padre
    init{
        this.numeroUno; numeroUno;
        this.numeroDos; numeroDos;
    }

    constructor( // Constructor secundario
        uno: Int?,
        dos: Int
    ) : this( // LLama a constructor primario
        if(uno == null) 0 else uno,
        dos
    ){ // si necesitamos bloque de codigo le usamos
        numeroUno
    }

    constructor(// tercer constructor
        uno: Int,
        dos: Int?
    ) : this ( //llamada constructor primario
        uno,
        if(dos == null) 0 else uno
    )
    // si no lo necesitamos al bloque de codigo {}, lo omitimos

    constructor( //cuarto constructor
        uno: Int?,
        dos: Int?
    ): this( //llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else uno
    )

    public fun sumar(): Int{ //public por defecto
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

//singleton: utilizar una unica instancia de una sola clase

    companion object{  //atributos y metodos compartidos
        //entre las instancias
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int{
            return num * num
        }

        val historialSumas = arrayListOf<Int>()

        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }
}

//Funciones
/**
 * void => Unit => Se usa Unit si la funcion no
 * devuelve nada, tiene la misma funcionalidad que el
 * void en Java. Se puede omitir su escritura.
 */
//palabra reservada + nombreFuncion(parametros)
fun imprimirNombre(nombre: String): Unit{
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, // requerido
    tasa: Double = 12.00, //Opcional (valor por defecto)
    bonoEspecial: Double? = null // Opcion null => nullable
): Double{
    /**
     * int -> Int? (nullable)
     * String -> String? (nullable)
     * Date -> Date? (nullable)
     */
    if(bonoEspecial == null){
        return sueldo * (100 / tasa)
    }else{
        bonoEspecial.dec()
        return sueldo * (100 / tasa) * bonoEspecial
    }
}