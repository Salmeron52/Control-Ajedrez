package com.buenhijogames.controlpartidasajedrez.login

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buenhijogames.controlpartidasajedrez.AjedrezViewModel
import com.buenhijogames.controlpartidasajedrez.Alerta
import com.buenhijogames.controlpartidasajedrez.Espacio
import com.buenhijogames.controlpartidasajedrez.LoginViewModel
import com.buenhijogames.controlpartidasajedrez.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginView(
    loginViewModel: LoginViewModel,
    ajedrezViewModel: AjedrezViewModel,
    onNavigateToRegistro: () -> Unit,
    onNavigateToPrincipal: () -> Unit,
    onNavigateToTarjeta: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var oculto by remember { mutableStateOf(true) }

        ////////////////////////////////// POLÍTICA DE PRIVACIDAD //////////////////////////////////
        val comprasUrl = "https://sites.google.com/view/politica-buenhijogames"
        var buttonClicked by remember { mutableStateOf(false) }

        val launcher =
            rememberLauncherForActivityResult(
                contract = ActivityResultContracts
                    .StartActivityForResult()
            ) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // Se ha abierto la URL correctamente
                } else {
                    // Hubo un error al abrir la URL
                }
            }

        LaunchedEffect(buttonClicked) {
            if (buttonClicked) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(comprasUrl))
                launcher.launch(intent)
                buttonClicked = false // Restablecer el estado del botón
            }
        }
        ////////////////////////////////// POLÍTICA DE PRIVACIDAD //////////////////////////////////

        Espacio()

        Text("LOGIN", fontSize = 24.sp)

        Espacio()
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            maxLines = 1,

            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        )

        Espacio()

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            maxLines = 1,
            visualTransformation = if (oculto) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = {
                IconButton(onClick = { oculto = !oculto }) {
                    Icon(
                        painterResource(
                            if (oculto) R.drawable.icono_no_visible
                            else R.drawable.icono_visible
                        ),
                        contentDescription = "Ver contraseña"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        )

        Espacio(32)

        OutlinedButton(
            onClick = {
                loginViewModel.login(email, password) {
                    if (FirebaseAuth.getInstance().currentUser?.email.toString() == "manuelsalmeroncerdan@gmail.com") {
                        ajedrezViewModel.soyYo()
                        onNavigateToPrincipal()
                    } else {
                        onNavigateToTarjeta()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        ) {
            Text(text = "Entrar", fontSize = 20.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "¿No tienes cuenta?",
                fontSize = 16.sp,
            )

            TextButton(
                onClick = { onNavigateToRegistro() },
            ) { Text(text = "Regístrate", fontSize = 16.sp) }
        }

        if (loginViewModel.mostrarAlerta) {
            Alerta(
                titulo = "¡Atención!",
                mensaje = "Usuario o contraseña incorrectos",
                onConfirm = { loginViewModel.cerrarAlerta() }) {

            }
        }

    }

}