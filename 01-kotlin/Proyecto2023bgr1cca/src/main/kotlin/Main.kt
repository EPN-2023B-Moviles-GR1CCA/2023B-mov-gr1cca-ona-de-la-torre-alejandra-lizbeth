import java.util.*

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