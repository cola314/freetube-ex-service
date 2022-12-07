package org.goodgoodgood.freetubeexservice.dto.music;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddMusicDto {
    private String videoId;
    private String title;
}
