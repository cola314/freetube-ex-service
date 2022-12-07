package org.goodgoodgood.freetubeexservice.domain.music.repository

import org.goodgoodgood.freetubeexservice.domain.music.MusicPlaylist
import org.springframework.data.jpa.repository.JpaRepository

interface MusicPlaylistRepository: JpaRepository<MusicPlaylist, Long> {
}