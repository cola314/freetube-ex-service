package org.goodgoodgood.freetubeexservice.domain.common

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseTimestampEntity(

    @CreatedDate
    protected var created: LocalDateTime = LocalDateTime.MIN,

    @LastModifiedDate
    protected var updated: LocalDateTime = LocalDateTime.MIN,
)