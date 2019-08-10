package com.neugelb.themoviedb.model.entity

import androidx.room.Entity
import androidx.room.Ignore
import com.gmail.bishoybasily.recyclerview.EndlessRecyclerViewAdapter
import com.gmail.bishoybasily.recyclerview.RecyclerViewAdapter
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movies", primaryKeys = ["id"])
open class Movie(var id: String) : RecyclerViewAdapter.Item, Serializable {

    var title: String? = null
    @SerializedName("poster_path")
    var posterUrl: String? = null
    @SerializedName("backdrop_path")
    var backdrop: String? = null
    var overview: String? = null
    @SerializedName("release_date")
    var date: String? = null
    @SerializedName("adult")
    var isAdult: Boolean = false
    @SerializedName("vote_average")
    var votes: Double? = null

    @Ignore
    var saved: Boolean = false

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return id
    }

    class Loader(id: String) : Movie(id), EndlessRecyclerViewAdapter.ItemLoader

}

enum class Source {
    UPCOMING,
    NOW_PLAYING,
    POPULAR,
    TOP_RATED
}