package com.bidyut.tech.galleryz.model

typealias ItemId = String

data class Item(
        val id: ItemId,
        val name: String,
        val accentColor: Int,
        val url: String,
        val width: Int? = 0,
        val height: Int? = 0,
        val imageRatio: Float = 0f,
        var columns: Int = 0
)
