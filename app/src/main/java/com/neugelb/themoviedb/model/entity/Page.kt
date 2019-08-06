package com.neugelb.themoviedb.model.entity

import com.google.gson.annotations.SerializedName

data class Page<T>(
    val results: List<T>,
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int
) {

    val isLastPage: Boolean
        get() = totalPages == page

}
