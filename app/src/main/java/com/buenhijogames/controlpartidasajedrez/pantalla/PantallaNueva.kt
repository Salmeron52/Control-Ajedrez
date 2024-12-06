package com.buenhijogames.controlpartidasajedrez.pantalla

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.buenhijogames.controlpartidasajedrez.AjedrezViewModel
import com.buenhijogames.controlpartidasajedrez.model.Estado

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaNueva(
    ajedrezViewModel: AjedrezViewModel,
    navController: NavController,
    modoOscuro: MutableState<Boolean>
) {

    LaunchedEffect(Unit) {
        ajedrezViewModel.traerTodasLasTarjetas()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Pantalla Nueva") },
                actions = {
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFC107),
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                )
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
        floatingActionButton = {
        }

    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(0.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val datosNuevos: List<Estado> by ajedrezViewModel.datosList.collectAsState()

            LazyVerticalStaggeredGrid(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(0.dp))
                    .padding(WindowInsets.statusBars.asPaddingValues()),
                columns = StaggeredGridCells.Fixed(1),
                contentPadding = it,
                userScrollEnabled = true
            ) {
                items(datosNuevos) {
                   /* CardNotas(
                        modifier = Modifier,
                        notasViewModel = notasViewModel,
                        navController = navController,
                        idDelDocumento = it.idDelDocumento,
                        titulo = it.titulo,
                        comentarios = it.contenido,
                        dia = it.dia,
                        fecha = it.fecha,
                        hora = it.hora
                    ) {
                        navController.navigate(
                            "EditarNota2/${it.idDelDocumento}/${it.importante}"
                        )
                    }*/
                }
            }
        }
    }

}

