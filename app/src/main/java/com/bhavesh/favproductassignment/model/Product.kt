package com.bhavesh.favproductassignment.model


data class Product(
    var addToCartButtonText: String? = null,
    val badges: List<String>? = null,
    var brand: String? = null,
    var citrusId: String? = null,
    var id: String? = null,
    var imageURL: String? = null,
    var isAddToCartEnable: Boolean? = null,
    var isDeliveryOnly: Boolean? = null,
    var isDirectFromSupplier: Boolean? = null,
    var isFindMeEnable: Boolean? = null,
    var isInTrolley: Boolean? = null,
    var isInWishlist: Boolean? = null,
    val messages: Messages? = null,
    val price: List<Price>? = null,
    val purchaseTypes: List<PurchaseType>? = null,
    var ratingCount: Double? = null,
    var saleUnitPrice: Double? = null,
    var title: String? = null,
    var totalReviewCount: Int? = null
)
