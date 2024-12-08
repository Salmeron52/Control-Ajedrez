package com.buenhijogames.controlpartidasajedrez

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buenhijogames.controlpartidasajedrez.model.ModeloDeUsuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    val auth: FirebaseAuth = Firebase.auth

    var mostrarAlerta by mutableStateOf(false)

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                auth
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            onSuccess() //Navegamos a Home
                        } else {
                            Log.i("ERROR EN FIREBASE", "USUARIO O CONTRASEÑA INCORRECTOS")
                            mostrarAlerta = true
                        }
                    }
            } catch (e: Exception) {
                Log.i("ERROR EN FUN LOGIN", "ERROR: ${e.localizedMessage}")
            }
        }
    }

    fun crearUsuario(
        email: String,
        password: String,
        nombreUsuario: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                auth
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            guardarUsuario(nombreUsuario)
                            onSuccess() //Navegamos a Home
                        } else {
                            Log.i("ERROR EN FIREBASE", "USUARIO NO CREADO")
                            mostrarAlerta = true
                        }
                    }
            } catch (e: Exception) {
                Log.i("ERROR EN FUN LOGIN", "ERROR: ${e.localizedMessage}")
            }
        }
    }

    private fun guardarUsuario(nombreDeUsuario: String) {

        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        viewModelScope.launch {
            val usuario = ModeloDeUsuario(
                usuarioId = id.toString(),
                email = email.toString(),
                nombreUsuario = nombreDeUsuario
            )

            FirebaseFirestore
                .getInstance()
                .collection("usuarios")
                .add(usuario)
                .addOnSuccessListener {
                    Log.i("¡Guardado!", "Usuario guardado en Firestore")
                }
                .addOnFailureListener {
                    Log.i("¡¡ERROR!!", "ERROR AL GUARDAR USUARIO EN FIRESTORE")
                }
        }

    }


    fun cerrarAlerta() {
        mostrarAlerta = false
    }


}