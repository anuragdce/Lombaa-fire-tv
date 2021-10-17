package com.freemovies.firetv.data.remote.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.freemovies.firetv.base.data.Mappable
import com.freemovies.firetv.base.extension.currentDateTime
import com.freemovies.firetv.base.extension.toDateTime
import com.freemovies.firetv.data.local.model.Movie

private const val SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"
private const val RELEASED_FORMAT = "yyyy-MM-dd"

@JsonIgnoreProperties(ignoreUnknown = true)
data class RemoteMovie(
    @JsonProperty("content")
    val content: Content? = null,
    @JsonProperty("credits")
    val credits: List<Credit>? = null,
    @JsonProperty("genres")
    val genres: List<String>? = null,
    @JsonProperty("id")
    val id: String? = null,
    @JsonProperty("longDescription")
    val longDescription: String? = null,
    @JsonProperty("rating")
    val rating: Rating? = null,
    @JsonProperty("releaseDate")
    val releaseDate: String? = null,
    @JsonProperty("shortDescription")
    val shortDescription: String? = null,
    @JsonProperty("tags")
    val tags: List<String>? = null,
    @JsonProperty("thumbnail")
    val thumbnail: String? = null,
    @JsonProperty("title")
    val title: String? = null
) : Mappable<Movie> {
    override fun map(): Movie {
        return Movie(
            id = id ?: "",
            posterUrl = thumbnail ?: "",
            backgroundUrl = thumbnail ?: "",
            videoUrl = content?.videos?.firstOrNull()?.url ?: "",
            duration = content?.duration ?: 0,
            title = title ?: "",
            desc = shortDescription ?: "",
            longDesc = longDescription ?: "",
            dateAdded = content?.dateAdded?.toDateTime(SERVER_FORMAT) ?: currentDateTime(),
            releaseDate = releaseDate?.toDateTime(RELEASED_FORMAT) ?: currentDateTime(),
            rating = rating?.rating ?: "",
            genres = genres ?: emptyList(),
            tags = tags ?: emptyList(),
            credits = credits?.map { com.freemovies.firetv.data.local.model.Credit(it.name ?: "", it.role ?: "") } ?: emptyList(),
            progress = 0
        )
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class Content(
    @JsonProperty("dateAdded")
    val dateAdded: String? = null,
    @JsonProperty("duration")
    val duration: Int? = null,
    @JsonProperty("trickPlayFiles")
    val trickPlayFiles: List<TrickPlayFile>? = null,
    @JsonProperty("videos")
    val videos: List<Video>? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Credit(
    @JsonProperty("name")
    val name: String? = null,
    @JsonProperty("role")
    val role: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Rating(
    @JsonProperty("rating")
    val rating: String? = null,
    @JsonProperty("ratingSource")
    val ratingSource: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TrickPlayFile(
    @JsonProperty("quality")
    val quality: String? = null,
    @JsonProperty("url")
    val url: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Video(
    @JsonProperty("quality")
    val quality: String? = null,
    @JsonProperty("url")
    val url: String? = null,
    @JsonProperty("videoType")
    val videoType: String? = null
)