package org.goodgoodgood.freetubeexservice.dto.music;

import lombok.Getter;
import org.goodgoodgood.freetubeexservice.domain.music.MusicPlaylist;
import org.goodgoodgood.freetubeexservice.domain.music.Playlist;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PlaylistDto {

    @Getter
    public static class MusicDto {
        private String videoId;
        private String title;

        public MusicDto(MusicPlaylist musicPlaylist) {
            this.videoId = musicPlaylist.getMusic().getVideoId();
            this.title = musicPlaylist.getMusic().getTitle();
        }
    }

    private String title;
    private List<MusicDto> musics;

    public PlaylistDto(Playlist playlist) {
        this.title = playlist.getTitle();
        this.musics = playlist.getMusicPlaylists().stream()
                .map(MusicDto::new)
                .collect(Collectors.toList());
    }
}
