package com.buenhijogames.controlpartidasajedrez.pantalla

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buenhijogames.controlpartidasajedrez.AjedrezViewModel
import com.buenhijogames.controlpartidasajedrez.model.Partida
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeGeneral
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeTotal

@Composable
fun SuperficiePartidas(modifier: Modifier = Modifier, ajedrezViewModel: AjedrezViewModel) {

    Surface(
        modifier = modifier
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        color = VerdeTotal,
        tonalElevation = 8.dp,
        shadowElevation = 16.dp
    ) {
        Column {
            Surface(
                modifier = modifier.padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                color = VerdeGeneral,
                tonalElevation = 8.dp,
                shadowElevation = 16.dp
            ) {

                Spacer(modifier = Modifier.height(64.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Parcial",
                        fontSize = 18.sp,
                        color = Color.Black,
                        textDecoration = TextDecoration.Underline
                    )
                    Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Luis", color = Color.Black)
                            Text("${ajedrezViewModel.puntosPartidaLuis}", color = Color.Black)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Manolo", color = Color.Black)
                            Text("${ajedrezViewModel.puntosPartidaManolo}", color = Color.Black)
                        }
                    }
                }

            }

            SuperficieIntroducirResultados(ajedrezViewModel = ajedrezViewModel)
        }

    }

}

