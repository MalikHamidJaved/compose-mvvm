package com.ecandle.todo.use_case

import android.util.Log
import com.ecandle.todo.model.LoggedInUser
import com.ecandle.todo.repository.LoginRepository
import com.ecandle.todo.util.DataState
import com.ecandle.todo.util.internetCheck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(

    private val repository: LoginRepository

) {

    operator fun invoke(Email: String, Password: String): Flow<DataState<LoggedInUser>> = flow {

        try {

            emit(DataState.Loading)

            val process = repository.login(Email, Password)
            process?.let {
                emit(DataState.Success(process!!))
            }?:run {
                emit(DataState.Error(Exception("Invalid user")))
            }

        } catch (e: HttpException) {
            emit(DataState.Error(Exception(e.localizedMessage ?: "General error")))
            Log.e("LOG :::", e.localizedMessage)
        } catch (e: IOException) {

            if (!internetCheck()) {

                emit(DataState.Error(Exception("No Internet")))
                Log.e("LOG :::", e.localizedMessage)

            }
        }
    }
}