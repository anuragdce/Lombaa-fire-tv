package com.freemovies.firetv.data.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.freemovies.firetv.base.data.Mappable
import com.freemovies.firetv.base.data.MappableFrom
import com.freemovies.firetv.data.local.model.Credit
import com.freemovies.firetv.data.local.model.Movie
import org.joda.time.DateTime

@Entity(tableName = "movies")
internal data class RoomMovie(
    @PrimaryKey
    val id: String,
    val posterUrl: String,
    val backgroundUrl: String,
    val videoUrl: String,
    val duration: Int,
    val title: String,
    val desc: String,
    val longDesc: String,
    val dateAdded: DateTime,
    val releaseDate: DateTime,
    val rating: String,
    val genres: List<String>,
    val tags: List<String>,
    val credits: List<Credit>,
    val progress: Int
) : Mappable<Movie> {

    override fun map(): Movie = Movie(
        id,
        posterUrl,
        backgroundUrl,
        videoUrl,
        duration,
        title,
        desc,
        longDesc,
        dateAdded,
        releaseDate,
        rating,
        genres,
        tags,
        credits,
        progress
    )

    companion object : MappableFrom<Movie, RoomMovie> {

        override fun mapFrom(input: Movie): RoomMovie =
            RoomMovie(
                input.id,
                input.posterUrl,
                input.backgroundUrl,
                input.videoUrl,
                input.duration,
                input.title,
                input.desc,
                input.longDesc,
                input.dateAdded,
                input.releaseDate,
                input.rating,
                input.genres,
                input.tags,
                input.credits,
                input.progress
            )
    }
}
