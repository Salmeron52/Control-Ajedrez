package com.buenhijogames.controlpartidasajedrez.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.buenhijogames.controlpartidasajedrez.AjedrezViewModel
import com.buenhijogames.controlpartidasajedrez.LoginViewModel
import com.buenhijogames.controlpartidasajedrez.login.LoginView
import com.buenhijogames.controlpartidasajedrez.login.RegisterView
import com.buenhijogames.controlpartidasajedrez.pantalla.GuardarResultados
import com.buenhijogames.controlpartidasajedrez.pantalla.PantallaPrincipal
import com.buenhijogames.controlpartidasajedrez.pantalla.Tarjeta

@Composable
fun Navegacion(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel,
    ajedrezViewModel: AjedrezViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Principal) {
        composable<Tarjeta> {
            Tarjeta(
                ajedrezViewModel = ajedrezViewModel
            ) {
                navController.navigate(Principal)
            }
        }
        /*Tarjeta( idDelDocumento =  , ajedrezViewModel = ajedrezViewModel) {
        navController.navigate(Principal)
    } }*/
        composable<Registro> {
            RegisterView(loginViewModel = loginViewModel) {
                navController.navigate(Login) {
                    popUpTo<Login> { inclusive = true }
                }
            }
        }
        composable<Login> {
            LoginView(
                loginViewModel,
                onNavigateToRegistro = { navController.navigate(Registro) }) {
                navController.navigate(Principal)
            }
        }
        composable<Principal> {
            PantallaPrincipal(
                ajedrezViewModel = ajedrezViewModel,
                onNavigateToTarjeta = { navController.navigate(Tarjeta) })
        }
        composable<GuardarResultados> {
            GuardarResultados(ajedrezViewModel = ajedrezViewModel) {
                navController.navigate(Tarjeta)
            }
        }
    }
}