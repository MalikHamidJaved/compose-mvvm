package com.ecandle.todo.repository

import com.ecandle.todo.model.LoggedInUser
import com.ecandle.todo.network.LoginDataSource
import javax.inject.Inject


/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository @Inject constructor(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {

        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    suspend fun login(username: String, password: String): LoggedInUser? {
        return dataSource.login(username,password)
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}