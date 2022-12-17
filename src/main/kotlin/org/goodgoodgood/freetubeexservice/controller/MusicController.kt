package org.goodgoodgood.freetubeexservice.controller

import org.goodgoodgood.freetubeexservice.config.auth.SessionUser
import org.goodgoodgood.freetubeexservice.domain.music.MusicService
import org.goodgoodgood.freetubeexservice.domain.music.dto.AddMusicDto
import org.goodgoodgood.freetubeexservice.domain.music.dto.CreatePlaylistDto
import org.goodgoodgood.freetubeexservice.domain.music.dto.PlaylistDto
import org.goodgoodgood.freetubeexservice.domain.music.dto.PlaylistsShortDto
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession

@RestController
class MusicController(
    private val httpSession: HttpSession,
    private val musicService: MusicService,
) {

    @GetMapping("api/playlists")
    fun getPlaylists(): List<PlaylistsShortDto> {
        val user = httpSession.getAttribute("user") as SessionUser
        return musicService.getPlaylistsByEmail(user.email)
    }

    @GetMapping("api/playlists/{playlistId}")
    fun getPlaylistDetail(@PathVariable playlistId: Long): PlaylistDto {
        val user = httpSession.getAttribute("user") as SessionUser
        return musicService.getPlaylist(user.email, playlistId)
    }

    @PostMapping("api/playlists")
    fun createPlaylist(@RequestBody dto: CreatePlaylistDto): Long {
        val user = httpSession.getAttribute("user") as SessionUser
        return musicService.createPlaylist(user.email, dto)
    }

    @PostMapping("api/playlists/{playlistId}/musics")
    fun addMusic(
        @PathVariable playlistId: Long,
        @RequestBody dto: AddMusicDto,
    ): Long {
        val user = httpSession.getAttribute("user") as SessionUser
        return musicService.addMusic(playlistId, user.email, dto)
    }

}