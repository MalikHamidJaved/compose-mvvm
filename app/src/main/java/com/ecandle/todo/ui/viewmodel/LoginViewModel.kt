package com.ecandle.todo.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecandle.todo.ui.state.LoginState
import com.ecandle.todo.util.DataState
import com.ecandle.todo.use_case.LoginUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

    private val loginUseCase: LoginUseCase,
    @ApplicationContext val context: Context,

    ) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    var job: Job? = null

    fun getUserLogin( email: String, password: String) {

        _state.value =
            LoginState(error = "", isLoading = true)
        if (email.trim().isEmpty() && password.trim().isEmpty()
        ) {

            _state.value =
                LoginState(error = "Values can't be empty!", isLoading = false)

            return
        }

        job?.cancel()

        job = viewModelScope.launch(Dispatchers.IO) {

            loginUseCase(Email = email,
                Password = password
            ).onEach { result ->

                when (result) {

                    is DataState.Loading -> {
                        _state.value = LoginState(
                            isLoading = true,
                            internet = false
                        )

                    }

                    is DataState.Success -> {
                        _state.value = LoginState(
                            isLoading = false,
                            internet = false,
                            loginList = result.data
                        )
                    }
                    is DataState.Error -> {
                        _state.value = LoginState(
                            isLoading = false,
                            internet = false,
                            error = result.exception.localizedMessage,
                            loginList = null
                        )
                    }


                }

            }.launchIn(viewModelScope)

        }

    }

    fun clearViewModel() {

        state.value.internet = false
        state.value.isLoading = false
        state.value.success = -1
        state.value.loginList = null
        state.value.error = ""

    }

}

