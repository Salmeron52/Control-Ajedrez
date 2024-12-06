package com.buenhijogames.controlpartidasajedrez.model

data class Estado(
    val id: String = "",
    val email: String = "",
    val fecha: String = "",
    val hora: String = "",
    val dia: String = "",
    val puntosPartidaLuis: Double = 0.0,
    val puntosPartidaManolo: Double = 0.0,
    var campeonatoLuis: Double = 0.0,
    var campeonatoManolo: Double = 0.0,
    var generalLuis: Double = 0.0,
    var generalManolo: Double = 0.0,
) {
    constructor() : this(
        id = "",
        email = "",
        fecha = "",
        hora = "",
        dia = "",
        puntosPartidaLuis = 0.0,
        puntosPartidaManolo = 0.0,
        campeonatoLuis = 0.0,
        campeonatoManolo = 0.0,
        generalLuis = 0.0,
        generalManolo = 0.0
    )
}