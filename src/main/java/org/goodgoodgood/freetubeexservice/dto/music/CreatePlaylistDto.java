package org.goodgoodgood.freetubeexservice.dto.music;

import lombok.Getter;

@Getter
public class CreatePlaylistDto {

    private String title;

    public CreatePlaylistDto(String title) {
        this.title = title;
    }
}
