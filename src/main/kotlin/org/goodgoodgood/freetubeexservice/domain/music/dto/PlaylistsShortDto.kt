package org.goodgoodgood.freetubeexservice.domain.music.dto

import org.goodgoodgood.freetubeexservice.domain.music.Playlist

data class PlaylistsShortDto(
    val id: Long,
    val title: String,
) {

    companion object {
        fun of(playlist: Playlist): PlaylistsShortDto {
            return PlaylistsShortDto(
                id = playlist.id!!,
                title = playlist.title,
            )
        }
    }
}