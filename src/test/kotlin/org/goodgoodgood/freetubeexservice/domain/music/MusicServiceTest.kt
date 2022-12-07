package org.goodgoodgood.freetubeexservice.domain.music;

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.goodgoodgood.freetubeexservice.domain.user.Role
import org.goodgoodgood.freetubeexservice.domain.user.User
import org.goodgoodgood.freetubeexservice.domain.user.UserRepository
import org.goodgoodgood.freetubeexservice.dto.music.AddMusicDto
import org.goodgoodgood.freetubeexservice.dto.music.CreatePlaylistDto
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MusicServiceTest @Autowired constructor(
    private val musicService: MusicService,
    private val userRepository: UserRepository,
    private val playListRepository: PlaylistRepository,
    private val musicRepository: MusicRepository,
    private val musicPlaylistRepository: MusicPlaylistRepository,
) {

    @AfterEach
    fun clean() {
        musicPlaylistRepository.deleteAll()
        playListRepository.deleteAll()
        musicRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    fun getPlaylistsByEmail() {
        // given
        val savedUser = userRepository.save(User("name", "email", Role.USER))
        playListRepository.saveAll(
            listOf(
                Playlist(savedUser, "title"),
            )
        )

        // when
        val result = musicService.getPlaylistsByEmail("email")

        // then
        assertThat(result).hasSize(1)
        assertThat(result[0].title).isEqualTo("title")
    }

    @Test
    fun createPlaylist() {
        // given
        userRepository.save(User("name", "email", Role.USER))
        val request = CreatePlaylistDto("title")

        // when
        musicService.createPlaylist("email", request)

        // then
        val playlist = playListRepository.findAll()
        assertThat(playlist).hasSize(1)
        assertThat(playlist[0].title).isEqualTo("title")
    }

    @Test
    fun addMusic() {
        // given
        val savedUser = userRepository.save(User("name", "email", Role.USER))
        val savedPlaylist = playListRepository.save(Playlist(savedUser, "playlist"))
        val request = AddMusicDto("videoId", "music title")

        // when
        musicService.addMusic(savedPlaylist.id, "email", request)

        // then
        val music = musicRepository.findAll()[0]
        assertThat(music.videoId).isEqualTo("videoId")
        assertThat(music.title).isEqualTo("music title")
    }

    @Test
    fun getPlaylist() {
        // given
        val savedUser = userRepository.save(User("name", "email", Role.USER))
        val savedPlaylist = playListRepository.save(Playlist(savedUser, "playlist"))
        val savedMusic = musicRepository.save(Music("videoId", "title"))
        musicPlaylistRepository.save(MusicPlaylist(savedMusic, savedPlaylist))
        
        // when
        val playlist = musicService.getPlaylist("email", savedPlaylist.id)

        // then
        assertThat(playlist.title).isEqualTo("playlist")
        assertThat(playlist.musics).hasSize(1)
        assertThat(playlist.musics[0].title).isEqualTo("title")
        assertThat(playlist.musics[0].videoId).isEqualTo("videoId")
    }
}