package org.goodgoodgood.freetubeexservice.domain.music

import org.goodgoodgood.freetubeexservice.domain.common.BaseTimestampEntity
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class MusicPlaylist(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "music_id")
    val music: Music,

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    val playlist: Playlist,
): BaseTimestampEntity()