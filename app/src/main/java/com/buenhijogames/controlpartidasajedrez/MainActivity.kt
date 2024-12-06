package com.buenhijogames.controlpartidasajedrez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.buenhijogames.controlpartidasajedrez.navigation.Navegacion
import com.buenhijogames.controlpartidasajedrez.pantalla.PantallaPrincipal
import com.buenhijogames.controlpartidasajedrez.ui.theme.ControlPartidasAjedrezTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ajedrezViewModel = AjedrezViewModel()
        val loginViewModel = LoginViewModel()
        enableEdgeToEdge()
        setContent {
            ControlPartidasAjedrezTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Navegacion(
                       modifier = Modifier.padding(innerPadding),
                       ajedrezViewModel = ajedrezViewModel,
                       loginViewModel = loginViewModel
                   )
                }
            }
        }
    }
}

