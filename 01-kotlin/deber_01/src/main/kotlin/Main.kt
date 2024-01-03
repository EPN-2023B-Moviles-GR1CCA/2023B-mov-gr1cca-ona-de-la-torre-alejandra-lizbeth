import javax.swing.JFrame
import javax.swing.SwingUtilities
import GUI.MainInterface

//import Model.Cocinero
//import Model.Comida
//import Repository.CocineroRepository
//import Repository.ComidaRepository
//import java.util.*
//
//fun main(){
//
//    val cocinero = CocineroRepository()
//    val comida = ComidaRepository()
//    var newChef = Cocinero(codigoUnico = "201910919", nombre = "Alejandra", apellido = "Oña",
//        edad = 17, fechaContratacion = Date(), salario = 1500.00, isMainChef = true, comidaRepository = comida)
////    var comida = Comida()
//
//
//
////    println("el cocineto fue contratado el ${cocinero.getFechaContratacion()}" )
////    println("codigo unico del cocinero: ${cocinero.codigoUnico}")
////    println("nombre del cocinero: ${cocinero.nombre}")
////
////    if(cocinero.edad != 0) {
////        println("edad del cocinero: ${cocinero.edad}")
////    }else{
////        println("No se puede registrar al cocinero, no tiene la edad suficiente")
////    }
//    println("El cocinero que se agrego es: ${cocinero.create(newChef).toString()}")
//    println("Los cocineros existentes son: ${cocinero.getChefs().toString()}")
//    println("El cocinero que busca es: ${cocinero.getChefByCodigoUnico("201910918").toString()}")
//
//    println("obtener por nombre completo: ${cocinero.getChefByNameAndLastname("Alejandra", "Oña").toString()}")
//    println("obtener por edad: ${cocinero.getChefByAge(23).toString()}")
//    println("obtener por salario: ${cocinero.getChefBySalary(1500.00).toString()}")
//
//    var newChef2 = Cocinero(codigoUnico = "201910918", nombre = "Alejandra Lizbeth", apellido = "Oña",
//        edad = 17, fechaContratacion = Date(), salario = 1500.00, isMainChef = true, comida)
//    println("Actualizar chef: ${cocinero.updateByCodigoUnico("201910919", newChef2)}")
//
//
//    println("Los cocineros existentes son: ${cocinero.getChefs().toString()}")
//
////        println("Eliminar chef: ${cocinero.deleteByCodigoUnico("201910918")}")
////    println("Los cocineros existentes son: ${cocinero.getChefs().toString()}")
//    val cocineroSeleccionado: Cocinero ?= cocinero.getChefByCodigoUnico("201910918")
//    val nuevaComida = Comida(
//        identificador = "COMIDA-01", nombre = "ENCEBOLLADO", fechaCreacion = Date(),
//        esPlatoDelDia = true, tipoCocina = "ECUATORIANA", cantidadProductos = 5,
//        precio = 3.00, cocinero = cocineroSeleccionado)
//    if (cocineroSeleccionado != null) {
//        cocineroSeleccionado.prepararComida(nuevaComida)
//        println("el plato creado: ${cocineroSeleccionado.prepararComida(nuevaComida)}")
//        println("Los platos existentes son: ${comida.getComidas()}")
//
//    }
//
//}

//fun main(){
////    var v= Ventana()
////    v.isVisible=true
//
//            var frame:JFrame = GUI()
//            frame.setSize(300,300)
//            frame.isVisible = true
//
//}
//
//public class Main{
//    public static void main(String [] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run(){
//                JFrame frame = new GUI();
//                frame.setSize(300,300);
//                frame.setVisible(true);
//            }
//        })
//    }
//}

fun main() {
    SwingUtilities.invokeLater {
        val frame: JFrame = MainInterface()
        frame.title = "Aplicación cocinero comidas"
        frame.setLocation(600,250)
        frame.setSize(400, 300)
        frame.isResizable = false
        frame.isVisible = true
    }
}