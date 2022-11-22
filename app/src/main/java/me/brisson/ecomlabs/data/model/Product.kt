package me.brisson.ecomlabs.data.model

import java.math.BigDecimal
import java.util.UUID

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val image: String,
    val currentPrice: BigDecimal,
    val oldPrices: List<BigDecimal>? = emptyList()
)

val mockedProduct = Product(
    name = "Camera Polaroid - One Step2",
    currentPrice = BigDecimal(300.0),
    oldPrices = listOf(BigDecimal(450.0)),
    image = "https://images.unsplash.com/photo-1526170375885-4d8ecf77b99f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"
)