package io.github.ermadmi78.kobby.cinema.api.kobby.kotlin.entity

import io.github.ermadmi78.kobby.cinema.api.kobby.kotlin.fetchFilm

/**
 * Created on 21.05.2021
 *
 * @author Dmitry Ermakov (ermadmi78@gmail.com)
 */

suspend fun Film.refresh(__projection: (FilmProjection.() -> Unit)? = null): Film =
    __context().fetchFilm(id) {
        __projection?.invoke(this) ?: __withCurrentProjection()
    }

suspend fun Film.addActor(actorId: Long): Boolean = __context().mutation {
    associate(id, actorId)
}.associate

suspend fun Film.tag(tagValue: String): Boolean = __context().mutation {
    tagFilm(id, tagValue)
}.tagFilm