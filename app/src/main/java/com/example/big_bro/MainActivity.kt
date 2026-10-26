package com.example.big_bro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.big_bro.ui.theme.BigBroTheme
import kotlinx.coroutines.android.awaitFrame

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BigBroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .background(Color.Blue)
                            .padding(16.dp)
                    ) {
                        Greeting(
                            name = "67naldo",
                            modifier = Modifier.padding(innerPadding)
                        )
                        Contador(
                            modifier = Modifier
                                .background(Color.Red)
                                .padding(16.dp)
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BigBroTheme {
        Greeting("Android")
    }
}

@Composable
fun Contador(modifier: Modifier = Modifier, textModifier: Modifier = Modifier) {
    var contador by remember { mutableIntStateOf(0) }
    Button(
        onClick = { contador++ },
        modifier = modifier
    ) {
        Text(
            "Cliques: $contador",
            modifier = textModifier
        )
    }
}