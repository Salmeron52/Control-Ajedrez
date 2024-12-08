package com.buenhijogames.controlpartidasajedrez.pantalla

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.buenhijogames.controlpartidasajedrez.AjedrezViewModel
import com.buenhijogames.controlpartidasajedrez.Boton
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeOscuro
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeTotal
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Tarjeta(
    modifier: Modifier = Modifier,
    ajedrezViewModel: AjedrezViewModel,
    onNavigateToPrincipal: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    LaunchedEffect(Unit) { ajedrezViewModel.traerUltimaTarjeta() }

    val clipBoard = LocalClipboardManager.current
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = modifier.padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            color = VerdeTotal,
            tonalElevation = 8.dp,
            shadowElevation = 16.dp
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                SuperficieCampeonato(ajedrezViewModel = ajedrezViewModel)
                SuperficieGeneral(ajedrezViewModel = ajedrezViewModel)

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Boton(
                        color = VerdeOscuro,
                        texto = "Cerrar sesión",
                        onClick = {
                            ajedrezViewModel.puntosACero()
                            if (ajedrezViewModel.soyYo()) {
                                onNavigateToPrincipal()
                            } else {
                                ajedrezViewModel.cerrarSesion()
                                onNavigateToLogin()
                            }
                        }
                    )
                    Spacer(modifier = Modifier.width(64.dp))
                    Portapapeles(
                        clipBoard = clipBoard,
                        ajedrezViewModel = ajedrezViewModel,
                        context = context
                    )
                }
            }


        }
    }
}

