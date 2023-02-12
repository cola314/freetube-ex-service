package org.goodgoodgood.freetubeexservice.domain.music

import org.goodgoodgood.freetubeexservice.domain.music.dto.AddMusicDto
import org.goodgoodgood.freetubeexservice.domain.music.dto.CreatePlaylistDto
import org.goodgoodgood.freetubeexservice.domain.music.dto.PlaylistDto
import org.goodgoodgood.freetubeexservice.domain.music.dto.PlaylistsShortDto
import org.goodgoodgood.freetubeexservice.domain.music.repository.MusicPlaylistRepository
import org.goodgoodgood.freetubeexservice.domain.music.repository.MusicRepository
import org.goodgoodgood.freetubeexservice.domain.music.repository.PlaylistRepository
import org.goodgoodgood.freetubeexservice.domain.user.UserRepository
import org.goodgoodgood.freetubeexservice.exception.PlaylistNotFoundException
import org.goodgoodgood.freetubeexservice.exception.UserNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.function.Supplier

@Service
class MusicService(
    private val playlistRepository: PlaylistRepository,
    private val musicPlaylistRepository: MusicPlaylistRepository,
    private val userRepository: UserRepository,
    private val musicRepository: MusicRepository,
) {

    @Transactional
    fun getPlaylistsByEmail(email: String?): List<PlaylistsShortDto> {
        return playlistRepository.findAllByUserEmail(email)
            .map(PlaylistsShortDto::of)
    }

    @Transactional
    @Throws(Exception::class)
    fun createPlaylist(email: String, dto: CreatePlaylistDto): Long {
        val user = userRepository.findByEmail(email)
            .orElseThrow { UserNotFoundException() }
        val playlist = Playlist(
            user = user,
            title = dto.title,
        )
        playlistRepository.save(playlist)
        return playlist.id!!
    }

    @Transactional
    fun addMusic(playlistId: Long, email: String, dto: AddMusicDto): Long {
        val playlist: Playlist = playlistRepository.findById(playlistId)
            .orElseThrow(Supplier { PlaylistNotFoundException() })
        if (playlist.user.email != email) throw UserNotFoundException()
        val music = Music(
            videoId = dto.videoId,
            title = dto.title
        )
        val musicPlaylist = MusicPlaylist(
            playlist = playlist,
            music = music
        )
        musicRepository.save(music)
        musicPlaylistRepository.save(musicPlaylist)
        return playlistId
    }

    @Transactional
    fun getPlaylist(email: String, playlistId: Long): PlaylistDto {
        val playlist: Playlist = playlistRepository.findById(playlistId)
            .orElseThrow(Supplier { PlaylistNotFoundException() })
        if (playlist.user.email != email) throw UserNotFoundException()
        return PlaylistDto.of(playlist)
    }

}