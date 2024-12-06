package com.buenhijogames.controlpartidasajedrez.model

data class Partida(
    val id: Int = 0,
    val ganaLuis: Boolean = false,
    val ganaManolo: Boolean = false,
    val tablas: Boolean = false,
    val finalizarSesion: Boolean = false
)
