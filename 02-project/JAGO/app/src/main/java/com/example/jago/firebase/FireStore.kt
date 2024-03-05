package com.example.jago.firebase

import com.example.jago.RegistrarseActivity
import com.example.jago.models.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
}