package org.goodgoodgood.freetubeexservice.domain.music.dto

import org.goodgoodgood.freetubeexservice.domain.music.Playlist

data class PlaylistDto(
    val id: Long,
    val title: String,
    val musics: List<MusicDto>
) {
    companion object {
        fun of(playlist: Playlist): PlaylistDto {
            return PlaylistDto(
                id = playlist.id!!,
                title = playlist.title,
                musics = playlist.musicPlaylists.map { MusicDto.of(it.music) }
            )
        }
    }
}