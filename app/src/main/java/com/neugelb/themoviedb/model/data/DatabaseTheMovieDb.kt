package com.neugelb.themoviedb.model.data

import androidx.room.*
import com.neugelb.themoviedb.model.entity.Movie
import io.reactivex.Single


@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class DatabaseTheMovieDb : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO

    @Dao
    interface MovieDAO {

        @Query("SELECT * FROM movies")
        fun findAll(): Single<List<Movie>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun save(user: Movie)

        @Delete
        fun delete(user: Movie)

    }
}