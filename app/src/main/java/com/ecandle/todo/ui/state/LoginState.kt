package com.ecandle.todo.ui.state

import com.ecandle.todo.model.LoggedInUser


data class LoginState(
    var isLoading : Boolean = false,
    var success : Int = -1,
    var loginList : LoggedInUser? = null ,
    var error : String = "",
    var internet : Boolean = false
)