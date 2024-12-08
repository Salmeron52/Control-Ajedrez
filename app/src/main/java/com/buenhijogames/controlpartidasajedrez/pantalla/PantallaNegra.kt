package com.buenhijogames.controlpartidasajedrez.pantalla

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.buenhijogames.controlpartidasajedrez.navigation.Login
import com.buenhijogames.controlpartidasajedrez.navigation.Principal
import com.google.firebase.auth.FirebaseAuth

//Si el usuario no está logueado, navega a la pantalla de login,
// si está logueado, navega a la pantalla principal

@Composable
fun PantallaNegra(navController: NavController) {
    LaunchedEffect(Unit) {
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrBlank()) {
            navController.navigate(Login)
        } else {
            navController.navigate(Principal)
        }
    }
}