package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Recipe(
    @DrawableRes val imageResource: Int,
    val title: String,
    val ingredients: List<String>
)

val recipesList = listOf(
    Recipe(R.drawable.food, "Merienda", listOf("Pizza", "Pollo", "Cocacola")),
    Recipe(R.drawable.gofres, "Desayuno", listOf("Cerezas", "Café", "Margaritas")),
    Recipe(R.drawable.pasta, "Italiana", listOf("Espaguetis", "Macarones", "Rabiolis"))
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeColumnList(recipeList = recipesList)
        }
    }

    @Composable
    private fun RecipeColumnList(recipeList: List<Recipe>) {
        /*Lazy column es como un RecyclerView */
        LazyRow {
            //Le mandamos nuestra lista de recetas
            items(recipeList) { recipe ->
                RecipeCard(recipe = recipe)
            }
        }
    }


    @Composable
    private fun RecipeCard(recipe: Recipe) {
        val image = painterResource(id = recipe.imageResource)

        Card(
            shape = RoundedCornerShape(8.dp), elevation = 8.dp, modifier = Modifier.padding(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                val imageModifier = Modifier
                    .requiredHeight(150.dp)
                    .requiredWidth(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp))

                Image(
                    contentDescription = "Recipe Photo", painter = image, modifier = imageModifier,
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(text = recipe.title, style = MaterialTheme.typography.h6)

                for (ingredient in recipe.ingredients) {
                    Text(text = "• $ingredient", style = MaterialTheme.typography.body2)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun PreviewNewsStory() {
        RecipeColumnList(recipeList = recipesList)
    }
}