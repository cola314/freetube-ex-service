package org.goodgoodgood.freetubeexservice.domain.music.repository

import org.goodgoodgood.freetubeexservice.domain.music.Playlist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PlaylistRepository: JpaRepository<Playlist, Long> {
    @Query("select p from Playlist p where p.user.email = :email")
    fun findAllByUserEmail(email: String?): List<Playlist>
}