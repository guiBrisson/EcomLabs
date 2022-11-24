package me.brisson.ecomlabs.data.model

data class ProductsList(
    val title: String,
    val products: List<Product>,
    val componentType: ProductComponentType
)

enum class ProductComponentType {
    HORIZONTAL, VERTICAL, BIG
}

val mockedProductHorizontalList = ProductsList(
    title = "Produtos que você pode gostar",
    products = listOf(mockedProduct),
    componentType = ProductComponentType.HORIZONTAL
)

val mockedProductVerticalList = ProductsList(
    title = "Produtos que você pode gostar",
    products = listOf(mockedProduct, mockedProduct, mockedProduct),
    componentType = ProductComponentType.VERTICAL
)
