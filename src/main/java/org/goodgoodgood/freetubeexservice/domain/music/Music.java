package org.goodgoodgood.freetubeexservice.domain.music;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.goodgoodgood.freetubeexservice.domain.common.BaseTimestampEntity;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Music extends BaseTimestampEntity {

    @Id
    @Column(name = "music_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String videoId;

    @Column(nullable = false, updatable = false)
    private String title;

    @Builder
    public Music(String videoId, String title) {
        this.videoId = videoId;
        this.title = title;
    }
}
