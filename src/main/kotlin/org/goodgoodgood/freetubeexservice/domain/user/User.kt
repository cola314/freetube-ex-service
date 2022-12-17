package org.goodgoodgood.freetubeexservice.domain.user

import org.goodgoodgood.freetubeexservice.domain.common.BaseTimestampEntity
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    val email: String,

    var picture: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: Role,
): BaseTimestampEntity() {
    fun update(name: String, picture: String?): User {
        this.name = name
        this.picture = picture
        return this
    }

    companion object {
        fun fixture(
            name: String = "name",
            email: String = "email",
            role: Role = Role.USER,
        ): User {
            return User(
                name = name,
                email = email,
                role = role,
            )
        }
    }
}