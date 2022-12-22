package org.goodgoodgood.freetubeexservice.controller

import org.goodgoodgood.freetubeexservice.domain.music.MusicService
import org.goodgoodgood.freetubeexservice.domain.music.dto.AddMusicDto
import org.goodgoodgood.freetubeexservice.domain.music.dto.CreatePlaylistDto
import org.goodgoodgood.freetubeexservice.domain.music.dto.PlaylistDto
import org.goodgoodgood.freetubeexservice.domain.music.dto.PlaylistsShortDto
import org.goodgoodgood.freetubeexservice.domain.user.User
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession

@RestController
class MusicController(
    private val httpSession: HttpSession,
    private val musicService: MusicService,
) {

    @GetMapping("api/playlists")
    fun getPlaylists(
        @AuthenticationPrincipal user: User,
    ): List<PlaylistsShortDto> {
        return musicService.getPlaylistsByEmail(user.email)
    }

    @GetMapping("api/playlists/{playlistId}")
    fun getPlaylistDetail(
        @PathVariable playlistId: Long,
        @AuthenticationPrincipal user: User,
    ): PlaylistDto {
        return musicService.getPlaylist(user.email, playlistId)
    }

    @PostMapping("api/playlists")
    fun createPlaylist(
        @RequestBody dto: CreatePlaylistDto,
        @AuthenticationPrincipal user: User,
    ): Long {
        return musicService.createPlaylist(user.email, dto)
    }

    @PostMapping("api/playlists/{playlistId}/musics")
    fun addMusic(
        @PathVariable playlistId: Long,
        @RequestBody dto: AddMusicDto,
        @AuthenticationPrincipal user: User,
    ): Long {
        return musicService.addMusic(playlistId, user.email, dto)
    }

}