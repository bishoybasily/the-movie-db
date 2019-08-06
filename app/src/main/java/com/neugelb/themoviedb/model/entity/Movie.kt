package com.neugelb.themoviedb.model.entity

import androidx.room.Entity
import androidx.room.Ignore
import com.gmail.bishoybasily.recyclerview.RecyclerViewAdapter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies", primaryKeys = ["id"])
data class Movie(
    val id: String,
    val title: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("backdrop_path")
    val backdrop: String,
    val overview: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("adult")
    val isAdult: Boolean,
    @SerializedName("vote_average")
    val votes: Double
) : RecyclerViewAdapter.Item {

    @Ignore
    var source: Source? = null

}

enum class Source {
    LATEST,
    UPCOMING,
    NOW_PLAYING,
    POPULAR,
    TOP_RATED
}