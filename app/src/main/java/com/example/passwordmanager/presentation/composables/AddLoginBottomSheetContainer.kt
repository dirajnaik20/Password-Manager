package com.example.passwordmanager.presentation.composables

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.R
import com.example.passwordmanager.presentation.model.LoginUiInfo


@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun AddLoginBottomSheetContainer(
    saveLogin: (LoginUiInfo) -> Boolean,
    getLoginType: () -> MutableState<String>,
    getLoginUsername: () -> MutableState<String>,
    getLoginPassword: () -> MutableState<String>,
    updateLoginType: (String) -> Unit,
    updateLoginUsername: (String) -> Unit,
    updateLoginPassword: (String) -> Unit,
) {

    val loginType by remember {
        mutableStateOf(getLoginType())
    }

    val loginUsername by remember {
        mutableStateOf(getLoginUsername())
    }

    val loginPassword by remember {
        mutableStateOf(getLoginPassword())
    }

    val context = LocalContext.current

    var showPassword by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .imePadding(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = loginType.value,
                onValueChange = {
                    updateLoginType(it)
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text(text = "Account Name")
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.fontColorGray),
                    unfocusedBorderColor = colorResource(id = R.color.fontColorGray),
                ),
            )


            Spacer(
                modifier = Modifier
                    .height(25.dp)
            )

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.fontColorGray),
                    unfocusedBorderColor = colorResource(id = R.color.fontColorGray),
                ),
                value = loginUsername.value,
                onValueChange = {
                    updateLoginUsername(it)
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text(text = "Username/ Email")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(25.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                value = loginPassword.value,
                onValueChange = {
                    updateLoginPassword(it)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.fontColorGray),
                    unfocusedBorderColor = colorResource(id = R.color.fontColorGray),
                ),
                label = {

                    Text(text = "Password")

                },
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                painterResource(id = R.drawable.ic_visibility),
                                contentDescription = "hide_password",
                                tint = Color.Black
                            )

                        }
                    } else {
                        IconButton(onClick = { showPassword = true }) {
                            Icon(
                                painterResource(id = R.drawable.ic_visibility_off),
                                contentDescription = "show_password"
                            )

                        }
                    }
                }
            )

            Spacer(
                modifier = Modifier
                    .height(25.dp)
            )

            Button(
                onClick = {
                    if (
                        saveLogin(
                            LoginUiInfo(
                                loginType = loginType.value,
                                loginUsername = loginUsername.value,
                                loginPassword = loginPassword.value
                            )
                        )
                    ) {
                        Toast
                            .makeText(context, "New Login Details Added!", Toast.LENGTH_LONG)
                            .show()
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {

                Text(text = "Add New Account")

            }

        }


    }
}
