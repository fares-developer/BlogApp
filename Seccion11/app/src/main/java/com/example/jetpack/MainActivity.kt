package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsStory()
        }
    }

    //Es recomendable que las funciones composables empiezan por mayúsuclas
    @Composable
    private fun NewsStory() {

        val image = painterResource(id = R.drawable.avatar)

        //creamos una variable para modificar las características de la imagen
        val imageModifier = Modifier
            .width(200.dp).height(200.dp)


        //Nos permite organizar vistas, es como un lineraLayout vertical
        //El modifier nos permite personalizar nuestro column
        Column(modifier = Modifier.padding(16.dp)) {
            Image(image,
                contentDescription = "Mi Avatar",
                modifier = imageModifier,
                contentScale = ContentScale.Crop)

            Text(text = "News Title")
            Text(text = "News Description")
            Text(text = "Footer")

        }
    }

    @Preview
    @Composable
    fun Preview() {
        NewsStory()
    }

}