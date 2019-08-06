package com.neugelb.themoviedb.model.entity

import androidx.room.Entity

@Entity(tableName = "movies", primaryKeys = ["id"])
data class Movie(val id: String)