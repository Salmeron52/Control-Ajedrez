package com.buenhijogames.controlpartidasajedrez

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buenhijogames.controlpartidasajedrez.model.Estado
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AjedrezViewModel : ViewModel() {

    val firestore = Firebase.firestore
    val auth: FirebaseAuth = Firebase.auth

    private val _datosList = MutableStateFlow<List<Estado>>(emptyList())
    val datosList = _datosList

    var estado: Estado by mutableStateOf(Estado())

    var puntosPartidaLuis by mutableDoubleStateOf(0.0)
    var puntosPartidaManolo by mutableDoubleStateOf(0.0)

    //fecha de hoy en formato String
    fun fechaHoy(): String {
        val fechaActual: Date = Calendar.getInstance().time
        val fecha = SimpleDateFormat("dd-MM-yyyy\n\n", Locale.getDefault())
        return fecha.format(fechaActual)
    }

    fun datosListados(datos: List<Estado>): String {
        var datosListados = ""
        datos.forEach {
            datosListados += "\n${it.dia}, ${it.fecha}. ${it.hora}\n\n" +
                    "General:\n Luis: ${it.generalLuis} - Manolo: ${it.generalManolo}\n" +
                    "Campeonato:\n Luis: ${it.campeonatoLuis} - Manolo: ${it.campeonatoManolo}\n" +
                    "Último resultado:\n Luis: ${it.puntosPartidaLuis} - Manolo: " +
                    "${it.puntosPartidaManolo}\n\n"
        }
        return datosListados
    }

    private fun formatearDiaSemana(): String? {
        val fechaActual: Date = Calendar.getInstance().time
        val fechaYHora = SimpleDateFormat("EEEE", Locale.getDefault())
        return fechaYHora.format(fechaActual)
    }

    //Pasamos la fecha a tipo String
    private fun formatearFecha(): String? {
        val fechaActual: Date = Calendar.getInstance().time
        val fechaYHora = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return fechaYHora.format(fechaActual)
    }

    private fun formatearHora(): String? {
        val fechaActual: Date = Calendar.getInstance().time
        val fechaYHora = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return fechaYHora.format(fechaActual)
    }

    fun luisGanaLaPartida() {
        puntosPartidaLuis++
    }

    fun rectificarLuisGanaLaPartida() {
        puntosPartidaLuis--
    }

    fun manoloGanaLaPartida() {
        puntosPartidaManolo++
    }

    fun rectificarManoloGanaLaPartida() {
        puntosPartidaManolo--
    }

    fun tablasEnLaPartida() {
        puntosPartidaLuis += 0.5
        puntosPartidaManolo += 0.5
    }

    fun rectificarTablasEnLaPartida() {
        puntosPartidaLuis -= 0.5
        puntosPartidaManolo -= 0.5
    }

    fun sumarCampeonatoLuis() {
        estado.campeonatoLuis++
    }

    fun sumarCampeonatoManolo() {
        estado.campeonatoManolo++
    }

    fun sumarTablasCampeonato() {
        estado.campeonatoLuis += 0.5
        estado.campeonatoManolo += 0.5
    }

    fun sumarGeneralLuis() {
        estado.generalLuis++
    }

    fun sumarGeneralManolo() {
        estado.generalManolo++
    }

    fun ponerACeroCampeonatos() {
        estado.campeonatoLuis = 0.0
        estado.campeonatoManolo = 0.0
    }

    fun traerUltimaTarjeta() {
        viewModelScope.launch {
            firestore
                .collection("partidas")
                .document("id_uno")
                .addSnapshotListener { consulta, _ ->
                    if (consulta != null) {
                        val nota = consulta.toObject(Estado::class.java)
                        estado = estado.copy(
                            id = "id_uno",
                            puntosPartidaLuis = nota?.puntosPartidaLuis ?: 0.0,
                            puntosPartidaManolo = nota?.puntosPartidaManolo ?: 0.0,
                            campeonatoLuis = nota?.campeonatoLuis ?: 0.0,
                            campeonatoManolo = nota?.campeonatoManolo ?: 0.0,
                            generalLuis = nota?.generalLuis ?: 0.0,
                            generalManolo = nota?.generalManolo ?: 0.0,
                            email = nota?.email ?: "",
                            fecha = nota?.fecha ?: "",
                            hora = nota?.hora ?: "",
                            dia = nota?.dia ?: ""
                        )
                    }
                }
        }
    }


    fun guardarCampeonato() {
        val email = auth.currentUser?.email
        val unCampeonato = hashMapOf(
            "campeonatoLuis" to estado.campeonatoLuis,
            "campeonatoManolo" to estado.campeonatoManolo,
            "email" to email.toString()
        )
        firestore
            .collection("campeonato")
            .document("id_uno")
            .set(unCampeonato) // sobreescritura de datos en la colección campeonato
    }

    fun guardarGeneral() {
        val email = auth.currentUser?.email
        val unGeneral = hashMapOf(
            "generalLuis" to estado.generalLuis,
            "generalManolo" to estado.generalManolo,
            "email" to email.toString()
        )
        firestore
            .collection("general")
            .document("id_uno")
            .set(unGeneral)
    }

    fun guardarUltimaTarjeta(
        puntosPartidaLuis: Double,
        puntosPartidaManolo: Double
    ) {
        val email = auth.currentUser?.email

        viewModelScope.launch(Dispatchers.IO) {
            val nuevaTarjeta = hashMapOf(
                "puntosPartidaLuis" to puntosPartidaLuis,
                "puntosPartidaManolo" to puntosPartidaManolo,
                "dia" to formatearDiaSemana(),
                "fecha" to formatearFecha(),
                "hora" to formatearHora(),
                "campeonatoLuis" to estado.campeonatoLuis,
                "campeonatoManolo" to estado.campeonatoManolo,
                "generalLuis" to estado.generalLuis,
                "generalManolo" to estado.generalManolo,
                "email" to email.toString()
            )

            firestore
                .collection("partidas")
                .document("id_uno")
                .set(nuevaTarjeta)
        }
    }

    fun puntosACero() {
        puntosPartidaLuis = 0.0
        puntosPartidaManolo = 0.0
    }

    fun cerrarSesion() {
        auth.signOut()
    }

    fun calcularCampeonato(
        estado: Estado,
        ajedrezViewModel: AjedrezViewModel
    ) {
        if ((estado.campeonatoLuis != estado.campeonatoManolo) &&
            ((estado.campeonatoLuis >= 5) || (estado.campeonatoManolo >= 5))
        ) {
            when {
                estado.campeonatoLuis > estado.campeonatoManolo -> {
                    ajedrezViewModel.sumarGeneralLuis()
                    ajedrezViewModel.ponerACeroCampeonatos()
                }

                estado.campeonatoLuis < estado.campeonatoManolo -> {
                    ajedrezViewModel.sumarGeneralManolo()
                    ajedrezViewModel.ponerACeroCampeonatos()
                }
            }
        }
    }

    fun sumarCampeonatos(ajedrezViewModel: AjedrezViewModel) {
        when {
            ajedrezViewModel.puntosPartidaLuis > ajedrezViewModel.puntosPartidaManolo -> {
                ajedrezViewModel.sumarCampeonatoLuis()
            }

            ajedrezViewModel.puntosPartidaLuis < ajedrezViewModel.puntosPartidaManolo -> {
                ajedrezViewModel.sumarCampeonatoManolo()
            }

            else -> {
                ajedrezViewModel.sumarTablasCampeonato()
            }
        }
    }


    fun traerTodasLasTarjetas() {
        val email = auth.currentUser?.email
        firestore
            .collection("partidas")
            .orderBy("fecha", Query.Direction.ASCENDING)
            .whereEqualTo("email", email.toString())
            .addSnapshotListener { consulta, error ->
                if (error != null) return@addSnapshotListener //Añadir un mensaje de alerta al usuario
                else {
                    Log.i("¡¡ERROR!!", "ERROR: ${error?.localizedMessage ?: "No hay error"}")
                }

                val documentos = mutableListOf<Estado>()

                if (consulta != null) {
                    for (documento in consulta) {
                        val miDocumento = documento
                            .toObject(Estado::class.java)
                            .copy(id = documento.id)
                        documentos.add(miDocumento)
                    }
                } else {
                    Log.i("¡¡ERROR!!", "ERROR: ${error?.localizedMessage ?: "No hay error"}")
                }
                _datosList.value = documentos
            }
    }

    fun onClickMenos(texto: String) {
        when (texto) {
            "Manolo" -> {
                rectificarManoloGanaLaPartida()
            }
            "Luis" -> {
                rectificarLuisGanaLaPartida()
            }
            "Tablas" -> {
                rectificarTablasEnLaPartida()
            }
        }

    }

    fun onClickMas(texto: String) {
        when (texto) {
            "Manolo" -> {
                manoloGanaLaPartida()
            }
            "Luis" -> {
                luisGanaLaPartida()
            }
            "Tablas" -> {
                tablasEnLaPartida()
            }
        }
    }

}