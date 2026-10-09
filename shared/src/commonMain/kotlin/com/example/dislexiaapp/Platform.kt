package com.example.dislexiaapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform