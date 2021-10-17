package com.freemovies.firetv.data.local.model

import org.joda.time.DateTime

data class Movie(
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
) : BaseContentItem
