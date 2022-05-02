package com.example.carrusel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class MainActivity : AppCompatActivity() {

    private val list = mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val carousel = findViewById<ImageCarousel>(R.id.carousel)

        list.add(CarouselItem("https://andro4all.com/hero/2021/05/Material-You-elementos.jpg?width=480&aspect_ratio=19:10&format=nowebp"))
        list.add(CarouselItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2SAxuGnCGlVDJbIb7KRjocs1DOw8lJzgoZQ&usqp=CAU"))
        list.add(CarouselItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIU5_q3SFN1QPRecmsicZ2J3IW_Xzvf11kzw&usqp=CAU"))

        carousel.addData(list)
    }
}