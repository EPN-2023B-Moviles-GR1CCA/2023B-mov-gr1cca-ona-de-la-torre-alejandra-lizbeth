package com.example.jago.firebase

import com.example.jago.RegistrarseActivity
import com.example.jago.models.Gasto
import com.example.jago.models.Ingreso
import com.example.jago.models.Usuario
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions

class FireStore {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun usuarioRegistrado(actividad: RegistrarseActivity, infoUsuario: Usuario){

        mFireStore.collection("usuarios")
            .document(obtenerIdUsuario())
            .set(infoUsuario, SetOptions.merge())
            .addOnSuccessListener {
                actividad.usuarioRegistradoEx()
            }

    }

    fun obtenerIdUsuario(): String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }

    fun agregarIngreso(nuevoIngreso: Ingreso): Task<Void> {
        val documentReference = mFireStore.collection("usuarios").document(obtenerIdUsuario())
            .collection("ingresos").document()
        return documentReference.set(nuevoIngreso)
    }

    fun agregarGasto(nuevoGasto: Gasto): Task<Void> {
        val documentReference = mFireStore.collection("usuarios").document(obtenerIdUsuario())
            .collection("gastos").document()
        return documentReference.set(nuevoGasto)
    }

    fun obtenerTodosLosIngresos(): Task<QuerySnapshot> {
        val query = mFireStore.collection("usuarios").document(obtenerIdUsuario())
            .collection("ingresos")

        return query.get()
    }

    fun obtenerTodosLosGastos(): Task<QuerySnapshot> {
        val query = mFireStore.collection("usuarios").document(obtenerIdUsuario())
            .collection("gastos")
        return query.get()
    }

}