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
                    return@Home loginViewModel.saveLoginDetails(it)
                },
                getSavedLoginList = {
                    return@Home loginViewModel.allLoginDetails
                },
                getLoginType = {
                    return@Home loginViewModel.loginType
                },
                getLoginUsername = {
                    return@Home loginViewModel.loginUsername
                },
                getLoginPassword = {
                    return@Home loginViewModel.loginPassword
                },
                updateLoginType = {
                    loginViewModel.updateLoginType(it)
                },
                updateLoginUsername = {
                    loginViewModel.updateLoginUsername(it)
                },
                updateLoginPassword = {
                    loginViewModel.updateLoginPassword(it)
                },
                deleteLogin = {
                    loginViewModel.deleteLoginDetails(it)
                },
                updateEditedLoginDetails = { login, loginUiInfo ->
                    return@Home loginViewModel.updateLoginDetails(login, loginUiInfo)
                },
                updateEditedLoginType = {
                    loginViewModel.updateLoginType(it)
                },
                updateEditedLoginUsername = {
                    loginViewModel.updateLoginUsername(it)
                },
                updateEditedLoginPassword = {
                    loginViewModel.updateLoginPassword(it)
                },
                getDecryptedPassword = {
                    return@Home loginViewModel.decryptThePassword(it)
                },
                getLoginTypeToEdit = {
                    loginViewModel.updateLoginType(it)
                    return@Home loginViewModel.loginType

                }, getLoginUsernameToEdit = {
                    loginViewModel.updateLoginUsername(it)
                    return@Home loginViewModel.loginUsername

                }, getLoginPasswordToEdit = {

                    loginViewModel.updateDecryptedLoginPasswordToEdit(it)
                    return@Home loginViewModel.loginPassword

                },
                resetTextFields = {
                    loginViewModel.resetLoginFields()
                }
            )
        }
    }

}

