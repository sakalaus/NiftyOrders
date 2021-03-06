package com.pa.niftyorders.domain.use_cases

data class OrderUseCases(
    val getTopProducts: GetTopProducts,
    val getProductsInCart: GetProductsInCart,
    val getProductsInGroup: GetProductsInGroup,
    val getFeaturedProductGroups: GetFeaturedProductGroups,
    val getPromotions: GetPromotions,
    val changeQuantityInCart: ChangeQuantityInCart,
    val addProductToCart: AddProductToCart,
    val createDemoData: CreateDemoData
)
