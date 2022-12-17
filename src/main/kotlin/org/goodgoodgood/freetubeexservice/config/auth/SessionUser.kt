package org.goodgoodgood.freetubeexservice.config.auth

import java.io.Serializable

class SessionUser(
    val name: String,
    val email: String,
    val picture: String?,
): Serializable