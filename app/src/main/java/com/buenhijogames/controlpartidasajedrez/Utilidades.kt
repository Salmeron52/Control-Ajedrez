package com.buenhijogames.controlpartidasajedrez

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeGeneral2
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeTotal

@Composable
fun Titulo(texto: String) {
    Text(texto, fontSize = 18.sp, color = Color.Black)
}

@Composable
fun Boton(
    color: Color = VerdeTotal,
    texto: String = "",
    modifier: Modifier = Modifier,
    onClick: (Double) -> Unit = {}
) {
    Button(
        onClick = { onClick(0.0) },
        colors = ButtonDefaults.buttonColors(color)
    ) { Text(texto, color = VerdeGeneral2) }
}

@Composable
fun Espacio(espacio: Int = 16) {
    Spacer(modifier = Modifier.height(espacio.dp))
}

@Composable
fun Alto(modifier: Modifier = Modifier, alto: Int = 16) {
    Spacer(modifier = Modifier.height(alto.dp))
}

@Composable
fun Ancho(ancho: Int = 8) {
    Spacer(modifier = Modifier.width(ancho.dp))
}

@Composable
fun Alerta(
    titulo: String,
    mensaje: String,
    textoBotonAceptar: String = "Aceptar",
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val scroll = rememberScrollState(0)

    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            OutlinedButton(
                onClick = { onConfirm() },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
                )
            ) {
                Text(text = textoBotonAceptar)
            }
        },
        title = { Text(text = titulo) },
        text = {
            Text(
                text = mensaje,
                textAlign = TextAlign.Justify,
                modifier = Modifier.verticalScroll(scroll)
            )
        }
    )
}


@Composable
fun DialogoConfirmarGuardar(siConfirmaGuardar: () -> Unit, siNoConfirma: () -> Unit) {

    AlertDialog(
        onDismissRequest = { siNoConfirma() },
        title = { Text(text = "Guardar Resultados") },
        text = { Text(text = "¿Seguro que desea guardar resultados?", fontSize = 18.sp) },
        confirmButton = {
            Button(
                onClick = { siConfirmaGuardar() },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(100.dp)
                )
            ) {
                Text(text = "Aceptar", fontSize = 16.sp)
            }
        },
        dismissButton = {
            Button(
                onClick = { siNoConfirma() },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(100.dp)
                )
            ) {
                Text(text = "Cancelar", fontSize = 16.sp)
            }
        }
    )

}

@Composable
fun ResultadoPartida(
    nombre: String,
    onClickMenos: (String) -> Unit,
    onClickMas: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "-",
            fontSize = 40.sp,
            color = VerdeTotal,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onClickMenos(nombre) }
        )
        Ancho()
        Text(
            text = nombre,
            color = Color.Black,
        )
        Ancho()
        Text(
            text = "+",
            fontSize = 24.sp,
            color = VerdeTotal,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onClickMas(nombre) }
        )
    }
}
