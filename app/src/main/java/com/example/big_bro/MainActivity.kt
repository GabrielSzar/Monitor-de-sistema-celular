package com.example.big_bro

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.big_bro.ui.theme.BigBroTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BigBroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        TelaBateria(
                            Modifier.border(2.dp,Color.Blue)
                                .padding(16.dp)
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun TelaBateria(modifier: Modifier = Modifier) {
    val contexto = LocalContext.current
    var dados by remember { mutableStateOf<DadosBateria?>(null) }

    LaunchedEffect(Unit) {
        while (true) {
            dados = lerBateria(contexto)
            delay(1.seconds)
        }

    }
    val d = dados
    if (d == null) {
        Text("Lendo Bateria...", modifier = modifier)
    } else {
        Column(modifier = modifier) {
            Text("Bateria: ${d.porcentagem}%")
            Text("Corrente: ${d.correnteMa} mA")
            Text("Tensão: ${"%.2f".format(d.tensaoV)} Volts")
            Text("Temperatura: ${d.temperaturaC} °C")
            Text("Potencia: ${"%.2f".format(d.watts)} Watts")
            Text(if (d.plugado) "Carregando" else "Desgarregando")
        }
    }
}