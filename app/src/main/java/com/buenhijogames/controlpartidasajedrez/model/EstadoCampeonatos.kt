package com.buenhijogames.controlpartidasajedrez.model

data class EstadoCampeonatos(

    val id: String = "",
    val email: String = "",
    val campeonatoLuisEnFirebase: Double = 0.0,
    val campeonatoManoloEnFirebase: Double = 0.0,
) {
    constructor() : this(
        id = "",
        email = "",
        campeonatoLuisEnFirebase = 0.0,
        campeonatoManoloEnFirebase = 0.0,
    )
}
