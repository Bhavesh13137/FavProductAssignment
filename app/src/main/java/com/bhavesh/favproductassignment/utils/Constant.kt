package com.bhavesh.favproductassignment.utils

object Constant {
    const val BASE_URL = "https://run.mocky.io/v3/"
    fun calculatePercentage(obtained: Double, total: Double): String {
        return "-".plus((obtained * 100 / total).toInt()).plus("%")
    }
}