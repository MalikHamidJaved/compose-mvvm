package com.ecandle.todo.network

import com.ecandle.todo.model.LoggedInUser
import javax.inject.Inject

import kotlin.collections.HashMap

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource @Inject constructor() {

    val dummyAdminMap = HashMap<String,String>()
    fun login(username: String, password: String): LoggedInUser? {
        makeDummyAdmins()
        return try {

            if(dummyAdminMap.contains(username)){
                LoggedInUser(username,username, dummyAdminMap.contains(username))
            }else{
                null
            }

        } catch (e: Throwable) {
            null
        }
    }

    private fun makeDummyAdmins() {
        dummyAdminMap.put("hamidmalik803@gmail.com","password");
        dummyAdminMap.put("ha1@gmail.com","password");
        dummyAdminMap.put("ha2@gmail.com","password");

    }

    fun logout() {
    }
}