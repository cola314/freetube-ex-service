package org.goodgoodgood.freetubeexservice.config.auth

import org.goodgoodgood.freetubeexservice.domain.user.Role
import org.goodgoodgood.freetubeexservice.domain.user.User

class OAuthAttributes(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val name: String,
    val email: String,
    val picture: String?,
) {

    companion object {
        fun of(
            registrationId: String,
            userNameAttributeName: String,
            attributes: Map<String, Any>,
        ): OAuthAttributes {
            return ofGoogle(userNameAttributeName, attributes)
        }

        private fun ofGoogle(
            userNameAttributeName: String,
            attributes: Map<String, Any>
        ): OAuthAttributes {
            return OAuthAttributes(
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                picture = attributes["picture"] as String?,
                attributes = attributes,
                nameAttributeKey = userNameAttributeName,
            )
        }
    }

    fun toEntity(): User {
        return User(null, name, email, null, Role.USER)
    }

}