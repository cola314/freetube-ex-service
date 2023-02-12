package org.goodgoodgood.freetubeexservice.domain.music

import org.goodgoodgood.freetubeexservice.domain.common.BaseTimestampEntity
import javax.persistence.*

@Entity
class Music(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, updatable = false)
    val videoId: String,

    @Column(nullable = false, updatable = false)
    val title: String

) : BaseTimestampEntity() {

    companion object {
        fun fixture(
            videoId: String = "videoId",
            title: String = "title",
        ): Music {
            return Music(
                videoId = videoId,
                title = title,
            )
        }
    }
}