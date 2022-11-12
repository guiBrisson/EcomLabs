package me.brisson.ecomlabs.data.model

import android.text.TextUtils
import android.util.Patterns

data class User(
    val name: String,
    val email: String,
    val image: String
) {
    init {
        validateEmail()
    }

    private fun validateEmail() {
        assert(
            !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        ) { "Email not valid" } // Throws an AssertionError if the value is false
    }
}

val mockedUser = User(
    name = "Jon Doe",
    email = "jondoe@mail.com",
    image = "https://images.unsplash.com/photo-1599566150163-29194dcaad36?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
)
