package com.buenhijogames.controlpartidasajedrez.pantalla

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buenhijogames.controlpartidasajedrez.AjedrezViewModel
import com.buenhijogames.controlpartidasajedrez.Boton
import com.buenhijogames.controlpartidasajedrez.Titulo
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeGeneral

@Composable
fun SuperficieIntroducirResultados(modifier: Modifier = Modifier, ajedrezViewModel: AjedrezViewModel) {
    Surface(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        color = VerdeGeneral,
        tonalElevation = 8.dp,
        shadowElevation = 16.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Titulo("Introducir resultados")
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Boton(
                        ajedrezViewModel = ajedrezViewModel,
                        texto = "Gana Luis"
                    ) { ajedrezViewModel.luisGanaLaPartida() }

                    Boton(ajedrezViewModel, texto = "Gana Manolo") {
                        ajedrezViewModel.manoloGanaLaPartida()
                    }
                }
                Boton(
                    ajedrezViewModel = ajedrezViewModel,
                    texto = "Tablas"
                ) { ajedrezViewModel.tablasEnLaPartida() }
            }
        }
    }
}

