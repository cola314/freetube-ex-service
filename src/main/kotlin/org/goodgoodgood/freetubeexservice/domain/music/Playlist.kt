package org.goodgoodgood.freetubeexservice.domain.music

import org.goodgoodgood.freetubeexservice.domain.common.BaseTimestampEntity
import org.goodgoodgood.freetubeexservice.domain.user.User
import javax.persistence.*

@Entity
class Playlist(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val title: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @OneToMany(mappedBy = "playlist")
    val musicPlaylists: List<MusicPlaylist> = listOf()

): BaseTimestampEntity() {

    companion object {
        fun fixture(
            user: User,
            title: String = "title",
        ): Playlist {
            return Playlist(
                user = user,
                title = title,
            )
        }
    }
}