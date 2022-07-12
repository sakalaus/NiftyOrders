package com.pa.niftyorders.domain.use_cases

data class OrderUseCases(
    val getTopProducts: GetTopProducts,
    val getProductsInCart: GetProductsInCart,
    val changeQuantityInCart: ChangeQuantityInCart,
    val addProductToCart: AddProductToCart,
    val createDemoData: CreateDemoData)
