package com.example.examen_ib.repository
import com.example.examen_ib.model.Cocinero
import com.example.examen_ib.model.Comida
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

class CocineroFirestore {

    val db = Firebase.firestore

    fun crearCocinero(newChef: Cocinero): Task<Void> {
        val documentReference = db.collection("cocineros").document(newChef.codigoUnico)
        return documentReference.set(newChef)
    }

    fun obtenerCocineros(callback: (ArrayList<Cocinero>) -> Unit) {
        val cocinerosList = ArrayList<Cocinero>()
        db.collection("cocineros")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val cocinero = document.toObject(Cocinero::class.java)
                    cocinero.codigoUnico = document.id
                    cocinerosList.add(cocinero)
                }
                callback(cocinerosList)
            }
            .addOnFailureListener { exception ->
                // Manejar errores
            }
    }

    fun consultarCocineroPorCodigoUnico(codigoUnico: String): Task<DocumentSnapshot> {
        val documentReference = db.collection("cocineros").document(codigoUnico)
        return documentReference.get()
    }

    fun actualizarCocineroPorCodigoUnico(datosActualizados: Cocinero): Task<Void> {
        val documentReference = db.collection("cocineros").document(datosActualizados.codigoUnico)
        val data = hashMapOf(
            "nombre" to datosActualizados.nombre,
            "apellido" to datosActualizados.apellido,
            "edad" to datosActualizados.edad,
            "fechaContratacion" to datosActualizados.fechaContratacion,
            "salario" to datosActualizados.salario,
            "isMainChef" to datosActualizados.isMainChef
        )

        return documentReference.set(data)
    }

    fun eliminarCocineroPorCodigoUnico(codigoUnico: String): Task<Void> {
        val documentReference = db.collection("cocineros").document(codigoUnico)
        return documentReference.delete()
    }

    //CRUD Comidas
    fun obtenerComidasPorCocinero(identificadorCocinero: String, callback: (ArrayList<Comida>) -> Unit) {
        val comidas = ArrayList<Comida>()
        val collectionReference = db.collection("cocineros").document(identificadorCocinero).collection("comidas")

        collectionReference.get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val comida = document.toObject(Comida::class.java)
                    comida?.identificador = document.id
                    comida?.let {
                        comidas.add(it)
                    }
                }
                callback(comidas)
            }
            .addOnFailureListener { exception ->
                // Manejar errores
            }
    }

    fun crearComida(newFood: Comida): Task<Void> {
        // Referencia al documento del cocinero
        val cocineroDocumentReference = db.collection("cocineros").document(newFood.codigoUnicoCocinero)

        // Referencia a la colección de comidas dentro del documento del cocinero
        val comidasCollectionReference = cocineroDocumentReference.collection("comidas")

        // Agregar la nueva comida a la colección de comidas
        val documentReference = comidasCollectionReference.document(newFood.identificador)

        return documentReference.set(newFood)
    }

    fun consultarComidaPorIdentificadorYCocinero(identificador: String, codigoUnicoCocinero: String): Task<DocumentSnapshot> {
        // Referencia al documento de la comida dentro de la colección del cocinero
        val documentReference = db.collection("cocineros").document(codigoUnicoCocinero)
            .collection("comidas").document(identificador)

        // Obtener la comida por identificador y código único del cocinero
        return documentReference.get()
    }

    fun actualizarComidaPorIdentificadorYCodigoCocinero(datosActualizados: Comida): Task<Void> {
        // Referencia al documento de la comida dentro de la colección del cocinero
        val documentReference = db.collection("cocineros").document(datosActualizados.codigoUnicoCocinero)
            .collection("comidas").document(datosActualizados.identificador)

        // Actualizar los datos de la comida en Firestore
        return documentReference.set(datosActualizados)
    }

    fun eliminarComidaPorIdentificadorYCodigoCocinero(codigoComida: String, codigoUnico: String): Task<Void> {
        // Referencia al documento de la comida dentro de la colección del cocinero
        val documentReference = db.collection("cocineros").document(codigoUnico)
            .collection("comidas").document(codigoComida)

        // Eliminar el documento de la comida de Firestore
        return documentReference.delete()
    }
}