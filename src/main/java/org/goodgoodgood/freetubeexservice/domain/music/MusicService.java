package org.goodgoodgood.freetubeexservice.domain.music;

import lombok.RequiredArgsConstructor;
import org.goodgoodgood.freetubeexservice.domain.user.User;
import org.goodgoodgood.freetubeexservice.domain.user.UserRepository;
import org.goodgoodgood.freetubeexservice.dto.music.AddMusicDto;
import org.goodgoodgood.freetubeexservice.dto.music.CreatePlaylistDto;
import org.goodgoodgood.freetubeexservice.dto.music.PlaylistDto;
import org.goodgoodgood.freetubeexservice.dto.music.PlaylistsShortDto;
import org.goodgoodgood.freetubeexservice.exception.PlaylistNotFoundException;
import org.goodgoodgood.freetubeexservice.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MusicService {

    private final PlaylistRepository playlistRepository;
    private final MusicPlaylistRepository musicPlaylistRepository;
    private final UserRepository userRepository;
    private final MusicRepository musicRepository;

    @Transactional
    public List<PlaylistsShortDto> getPlaylistsByEmail(String email) {
        return playlistRepository.findAllByUserEmail(email)
                .stream()
                .map(PlaylistsShortDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long createPlaylist(String email, CreatePlaylistDto dto) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        Playlist playlist = Playlist.builder()
                .user(user)
                .title(dto.getTitle())
                .build();

        playlistRepository.save(playlist);

        return playlist.getId();
    }

    public Long addMusic(Long playlistId, String email, AddMusicDto dto) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(PlaylistNotFoundException::new);

        if (!playlist.getUser().getEmail().equals(email))
            throw new UserNotFoundException();

        Music music = Music.builder()
                .videoId(dto.getVideoId())
                .title(dto.getTitle())
                .build();
        MusicPlaylist musicPlaylist = MusicPlaylist.builder()
                .playlist(playlist)
                .music(music)
                .build();

        musicRepository.save(music);
        musicPlaylistRepository.save(musicPlaylist);

        return playlistId;
    }

    public PlaylistDto getPlaylist(String email, Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(PlaylistNotFoundException::new);

        if (!playlist.getUser().getEmail().equals(email))
            throw new UserNotFoundException();

        return new PlaylistDto(playlist);
    }
}
