package com.ecandle.todo.ui.screens


import android.provider.SyncStateContract
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ecandle.todo.ui.Routes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ecandle.todo.ui.theme.Purple700
import com.ecandle.todo.ui.util.LoginUtils
import com.ecandle.todo.ui.viewmodel.LoginViewModel
import com.ecandle.todo.R
import com.ecandle.todo.ui.theme.JetpackComposeDemoTheme
import com.ecandle.todo.util.LoadingAnimation

@Composable
fun LoginPage(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current
    val username = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val isErrorEmailIcon = remember { mutableStateOf(false) }
    val isErrorEmailMessage = remember { mutableStateOf("") }
    val isErrorPasswordMessage = remember { mutableStateOf("") }



    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Sign up here"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = { navController.navigate(Routes.SignUp.route) },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = {
                isErrorEmailIcon.value = false
                username.value = it
            },
            trailingIcon = {
                if (!isErrorEmailMessage.value.isNullOrEmpty())
                    Icon(
                        Icons.Filled.Error,
                        isErrorEmailMessage.value,
                        tint = MaterialTheme.colors.error
                    )
            },
            isError = !isErrorEmailMessage.value.isNullOrEmpty()

        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            label = { Text(text = "Password") },
            trailingIcon = {
                if (!isErrorPasswordMessage.value.isNullOrEmpty())
                    Icon(
                        Icons.Filled.Error,
                        isErrorPasswordMessage.value,
                        tint = MaterialTheme.colors.error
                    )
            },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {
                isErrorPasswordMessage.value = ""
                password.value = it
            },
            isError = !isErrorPasswordMessage.value.isNullOrEmpty()

        )




        Spacer(modifier = Modifier.height(20.dp))
        if (state.isLoading) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {


                LoadingAnimation(speed = 4f)


            }
        } else {
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        when (LoginUtils().loginFormatValidation(
                            username.value.text,
                            password.value.text
                        )) {

                            LoginUtils.VALID_DATA -> {

                                viewModel.getUserLogin(
                                    username.value.text,
                                    password.value.text
                                )

                            }

                            LoginUtils.INVALID_EMAIL -> {

                                isErrorEmailIcon.value = true
                                isErrorEmailMessage.value =
                                    context.getString(R.string.invalid_email)

                            }

                            LoginUtils.INVALID_PASSWORD -> {

                                isErrorPasswordMessage.value =
                                    context.getString(R.string.invalid_password)

                            }


                        }

                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Login")
                }
            }
        }


        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Forgot password?"),
            onClick = { navController.navigate(Routes.ForgotPassword.route) },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default
            )
        )
    }

    state.loginList?.let {
        viewModel.clearViewModel()
        navController.navigate(Routes.Dashboard.route)
    }
}


@Preview
@Composable
fun loginPreview() {
}