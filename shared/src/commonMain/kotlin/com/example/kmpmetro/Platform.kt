package com.example.kmpmetro

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform