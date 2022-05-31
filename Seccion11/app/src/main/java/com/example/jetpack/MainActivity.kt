package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

        val image = painterResource(id = R.drawable.tezos)

        //creamos una variable para modificar las características de la imagen
        val imageModifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(shape = RoundedCornerShape(16.dp))//Bordes redondos


        //Nos permite organizar vistas, es como un lineraLayout vertical
        //El modifier nos permite personalizar nuestro column
        Column(modifier = Modifier.padding(16.dp)) {
            Image(image,
                contentDescription = "Mi Avatar",
                modifier = imageModifier,
                contentScale = ContentScale.Crop)

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Text(text = "News Title", style = MaterialTheme.typography.h6)
            Text(text = "News Description", style = MaterialTheme.typography.body1)
            Text(text = "Footer", style = MaterialTheme.typography.body2)

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        NewsStory()
    }

}