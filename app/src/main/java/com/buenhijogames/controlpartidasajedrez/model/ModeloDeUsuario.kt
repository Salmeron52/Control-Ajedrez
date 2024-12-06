package com.buenhijogames.controlpartidasajedrez.model

import androidx.annotation.Keep

@Keep
data class ModeloDeUsuario(
    val usuarioId: String = "",
    val email: String = "",
    val nombreUsuario: String = ""
) {
    constructor() : this("", "", "")

    fun mapear(): MutableMap<String, Any> {
        return mutableMapOf(
            "userid" to this.usuarioId,
            "email" to this.email,
            "username" to this.nombreUsuario
        )
    }
}