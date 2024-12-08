package com.buenhijogames.controlpartidasajedrez.pantalla

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buenhijogames.controlpartidasajedrez.AjedrezViewModel
import com.buenhijogames.controlpartidasajedrez.Boton
import com.buenhijogames.controlpartidasajedrez.DialogoConfirmarGuardar
import com.buenhijogames.controlpartidasajedrez.Titulo
import com.buenhijogames.controlpartidasajedrez.model.Estado
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeGeneral
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeTotal

@Composable
fun GuardarResultados(
    modifier: Modifier = Modifier,
    ajedrezViewModel: AjedrezViewModel,
    onNavigateToTarjeta: () -> Unit
) {
    LaunchedEffect(Unit) {
        ajedrezViewModel.traerUltimaTarjeta()
    }

    val estado: Estado = ajedrezViewModel.estado

    Surface(
        modifier = modifier
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        color = VerdeTotal,
        tonalElevation = 8.dp,
        shadowElevation = 16.dp
    ) {
        Surface(
            modifier = modifier.padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            color = VerdeGeneral,
            tonalElevation = 8.dp,
            shadowElevation = 16.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Titulo("Fin de la jornada")
                Boton(
                    texto = "Guardar resultados"
                ) {
                    if(ajedrezViewModel.hayResultado()) {
                        ajedrezViewModel.sumarCampeonatos(ajedrezViewModel)
                        ajedrezViewModel.guardarUltimaTarjeta(
                            ajedrezViewModel.puntosPartidaLuis,
                            ajedrezViewModel.puntosPartidaManolo
                        )
                        ajedrezViewModel.calcularCampeonato(estado, ajedrezViewModel)
                        onNavigateToTarjeta()
                    }

                }
            }
        }
    }
}



