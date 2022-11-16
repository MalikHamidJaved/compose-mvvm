package com.ecandle.todo.ui.util

class LoginUtils {

    companion object {
        val VALID_DATA = 1
        val INVALID_EMAIL = 2
        val INVALID_PASSWORD = 2
    }

    fun loginFormatValidation(email: String, password: String): Int {
        return if (email.trim().isNotEmpty()) {
            return if (email.length > 5) {
                return if (email.contains("@")) {
                    return if (password.trim().isNotEmpty()) {
                        VALID_DATA
                    } else {
                        INVALID_PASSWORD
                    }
                } else {
                     INVALID_EMAIL
                }
            } else {
                 INVALID_EMAIL
            }
        } else {
             INVALID_EMAIL
        }
    }
}