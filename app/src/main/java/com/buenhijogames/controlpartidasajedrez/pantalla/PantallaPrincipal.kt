package com.buenhijogames.controlpartidasajedrez.pantalla

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.buenhijogames.controlpartidasajedrez.AjedrezViewModel
import com.buenhijogames.controlpartidasajedrez.model.Estado
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeTotal

@Composable
fun PantallaPrincipal(
    modifier: Modifier = Modifier,
    ajedrezViewModel: AjedrezViewModel,
    onNavigateToTarjeta: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
                SuperficiePartidas(ajedrezViewModel = ajedrezViewModel)
                SuperficieGeneral(ajedrezViewModel = ajedrezViewModel)
                GuardarResultados(
                    ajedrezViewModel = ajedrezViewModel,
                    onNavigateToTarjeta = { onNavigateToTarjeta() })
            }
        }
    }
}