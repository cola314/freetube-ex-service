package org.goodgoodgood.freetubeexservice.domain.music;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.goodgoodgood.freetubeexservice.domain.common.BaseTimestampEntity;
import org.goodgoodgood.freetubeexservice.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Playlist extends BaseTimestampEntity {

    @Id
    @Column(name = "playlist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String title;

    @OneToMany(mappedBy = "playlist")
    private List<MusicPlaylist> musicPlaylists = new ArrayList<>();

    @Builder
    public Playlist(User user, String title) {
        this.user = user;
        this.title = title;
    }
}
