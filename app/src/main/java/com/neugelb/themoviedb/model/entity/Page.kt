package com.neugelb.themoviedb.model.entity

import com.google.gson.annotations.SerializedName

data class Page<T>(
    var results: List<T>,
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int
) {

    val hasMore: Boolean
        get() = totalPages > page

}
