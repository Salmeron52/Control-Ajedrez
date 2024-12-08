package com.buenhijogames.controlpartidasajedrez.pantalla

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buenhijogames.controlpartidasajedrez.AjedrezViewModel
import com.buenhijogames.controlpartidasajedrez.Titulo
import com.buenhijogames.controlpartidasajedrez.model.Estado
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeGeneral
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeTotal

@Composable
fun SuperficieCampeonato(modifier: Modifier = Modifier, ajedrezViewModel: AjedrezViewModel) {
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
            modifier = modifier.padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            color = VerdeGeneral,
            tonalElevation = 8.dp,
            shadowElevation = 16.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Titulo(texto = "Campeonato")
                Titulo(
                    "Luis: ${estado.campeonatoLuis}" +
                            " - Manolo: ${estado.campeonatoManolo}"
                )
            }
        }
    }

}

