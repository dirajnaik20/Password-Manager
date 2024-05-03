package com.example.passwordmanager.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.passwordmanager.presentation.LoginViewModel
import com.example.passwordmanager.presentation.screens.Home


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,
    loginViewModel: LoginViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) {
            Home(
                navController,
                saveLogin = {

                    loginViewModel.saveLogin(it)

                },
                getSavedLoginList = {
                    return@Home loginViewModel.savedLoginList
                },
                deleteLogin = {
                    loginViewModel.deleteLogin(it)
                },
                updateLogin = {
                    loginViewModel.updateLogin(it)
                },
                encryptPass = {
                    return@Home loginViewModel.encryptThePassword(it)

                },
                decryptPass = {
                              loginViewModel.decryptThePassword(it)
                },
                encryptEditedPass = {
                    loginViewModel.encryptThePassword(it)
                }
            )
        }
    }

}

