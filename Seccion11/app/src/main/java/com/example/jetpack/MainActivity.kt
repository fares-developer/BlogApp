package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting("Fares")
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(
            text = "Hola $name",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }

    @Preview //Indica que podemos previsualizar la interfaz
    @Composable
    fun previewText() {
        Greeting("Fares")
    }
}