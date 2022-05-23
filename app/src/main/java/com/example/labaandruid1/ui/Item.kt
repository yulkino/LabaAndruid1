package com.example.labaandruid1.ui

data class Item(
    val title: String,
    val subtitle: String,
    val price: Int,
    val onDelete: () -> Unit,
)