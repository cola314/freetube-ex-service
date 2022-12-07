package org.goodgoodgood.freetubeexservice.domain.music.dto

import org.goodgoodgood.freetubeexservice.domain.music.Music

data class MusicDto(
    val videoId: String,
    val title: String,
) {
    companion object {
        fun of(music: Music): MusicDto {
            return MusicDto(
                videoId = music.videoId,
                title = music.title,
            )
        }
    }
}