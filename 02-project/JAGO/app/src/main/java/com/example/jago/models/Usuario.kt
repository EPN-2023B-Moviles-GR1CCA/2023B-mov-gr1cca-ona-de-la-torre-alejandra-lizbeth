package com.example.jago.models

import android.os.Parcel
import android.os.Parcelable

data class Usuario (
    val id: String = "",
    val nombres: String = "",
    val apellidos: String = "",
    val telefono: Long = 0,
    val correo: String = "",


): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readString()!!
    ) {
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, p1: Int) = with(dest){
        writeString(id)
        writeString(nombres)
        writeString(apellidos)
        writeLong(telefono)
        writeString(correo)
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}