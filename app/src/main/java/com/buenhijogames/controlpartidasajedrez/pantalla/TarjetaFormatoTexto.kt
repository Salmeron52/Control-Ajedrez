package com.buenhijogames.controlpartidasajedrez.pantalla

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.buenhijogames.controlpartidasajedrez.AjedrezViewModel
import com.buenhijogames.controlpartidasajedrez.R
import com.buenhijogames.controlpartidasajedrez.model.Estado
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeGeneral2
import com.buenhijogames.controlpartidasajedrez.ui.theme.VerdeTotal


@Composable
fun Portapapeles(
    clipBoard: ClipboardManager,
    ajedrezViewModel: AjedrezViewModel,
    context: Context,
    modifier: Modifier = Modifier
) {

    IconButton(
        onClick = {
            //Vaciamos el portapapeles
            clipBoard.setText(AnnotatedString(text = ""))
            clipBoard.setText(
                AnnotatedString(
                    text = ajedrezViewModel.fechaHoy() + ajedrezViewModel.datosListados(
                        ajedrezViewModel.datosList.value
                    )
                )
            )

            Toast.makeText(
                /* context = */ context,
                /* text = */ "Enviado al portapapeles",
                /* duration = */ Toast.LENGTH_SHORT
            ).show()
        },
    ) {
        Icon(
            painterResource(id = R.drawable.portapapeles),
            contentDescription = "Clipboard",
            modifier = Modifier.size(32.dp),
            tint = VerdeGeneral2
        )
    }
}