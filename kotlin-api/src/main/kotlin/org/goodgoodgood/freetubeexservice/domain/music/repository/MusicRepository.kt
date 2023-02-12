package org.goodgoodgood.freetubeexservice.domain.music.repository

import org.goodgoodgood.freetubeexservice.domain.music.Music
import org.springframework.data.jpa.repository.JpaRepository

interface MusicRepository: JpaRepository<Music, Long> {
}