package com.buenhijogames.controlpartidasajedrez.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.buenhijogames.controlpartidasajedrez.Alerta
import com.buenhijogames.controlpartidasajedrez.Espacio
import com.buenhijogames.controlpartidasajedrez.LoginViewModel
import com.buenhijogames.controlpartidasajedrez.R

@Composable
fun RegisterView(loginViewModel: LoginViewModel, onNavigateToLogin: () -> Unit) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var username by remember { mutableStateOf("") }
        var oculto by remember { mutableStateOf(true) }



        Text("REGISTRO", fontSize = 24.sp)
        Espacio()

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Nombre de usuario") },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        )

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
                loginViewModel.crearUsuario(email, password, username) {
                    onNavigateToLogin()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        ) {
            Text(text = "Registrarse", fontSize = 20.sp)
        }

        if (loginViewModel.mostrarAlerta) {
            Alerta(
                titulo = "¡Atención!",
                mensaje = "Usuario no creado",
                onConfirm = { loginViewModel.cerrarAlerta() }) {

            }
        }

    }


}