package com.ecandle.todo.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ecandle.todo.ui.Routes
import com.example.jetpackcomposedemo.screen.ForgotPassword


    @Composable
    fun ScreenMain(){
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Routes.Login.route) {

            composable(Routes.Login.route) {
                LoginPage(navController = navController)
            }

            composable(Routes.SignUp.route) {
                SignUp(navController = navController)
            }

            composable(Routes.ForgotPassword.route) { navBackStack ->
                ForgotPassword(navController = navController)
            }

            composable(Routes.Dashboard.route) {
                Dashboard(navController = navController)
            }

            composable(Routes.Home.route) {
                Home(navController = navController)
            }
        }
    }
