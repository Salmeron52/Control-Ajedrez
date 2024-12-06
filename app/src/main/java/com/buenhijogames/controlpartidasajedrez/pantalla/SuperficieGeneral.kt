package com.buenhijogames.controlpartidasajedrez.pantalla

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buenhijogames.controlpartidasajedrez.AjedrezViewModel
import com.buenhijogames.controlpartidasajedrez.model.Estado
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeGeneral
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeTotal

@Composable
fun SuperficieGeneral(modifier: Modifier = Modifier, ajedrezViewModel: AjedrezViewModel) {
    LaunchedEffect(Unit) {
        /*ajedrezViewModel.traerGeneralManolo()
        ajedrezViewModel.traerGeneralLuis()*/
        ajedrezViewModel.traerTodasLasTarjetas()
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
            color =  VerdeGeneral,
            tonalElevation = 8.dp,
            shadowElevation = 16.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("GENERAL", fontSize = 18.sp, color = Color.Black)
                Text("Luis: ${estado.generalLuis}" +
                        " - Manolo: ${estado.generalManolo}", color = Color.Black, fontSize = 16.sp)
            }
        }
    }

}