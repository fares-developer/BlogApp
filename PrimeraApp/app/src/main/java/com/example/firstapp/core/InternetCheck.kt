package com.example.firstapp.core

import kotlinx.coroutines.coroutineScope
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object InternetCheck {

    //coroutineScope es utilizado para trabajar con corutinas asíncornamente
    suspend fun isNetworkAvailable(): Boolean = coroutineScope {
        return@coroutineScope try {
            val socket = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)
            socket.connect(socketAddress, 2000)
            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }
}