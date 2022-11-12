package me.brisson.ecomlabs.util

import me.brisson.ecomlabs.data.model.User
import me.brisson.ecomlabs.data.model.mockedUser

object CurrentUser {
    private var currentUser: User? = mockedUser

    operator fun invoke(user: User) {
        currentUser = user
    }

    operator fun invoke() = currentUser

    fun isLoggedIn() : Boolean = currentUser != null

    fun logout() {
        currentUser = null
    }
}
