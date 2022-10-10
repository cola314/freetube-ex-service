package org.goodgoodgood.freetubeexservice.dto.music;

import org.goodgoodgood.freetubeexservice.domain.music.Playlist;

public class PlaylistsShortDto {

    private Long id;
    private String title;

    public PlaylistsShortDto(Playlist playlist) {
        this.id = playlist.getId();
        this.title = playlist.getTitle();
    }
}
