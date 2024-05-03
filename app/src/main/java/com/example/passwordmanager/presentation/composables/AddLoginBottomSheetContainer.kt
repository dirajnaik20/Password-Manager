package com.example.passwordmanager.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.data.local.Login
import com.example.passwordmanager.utils.Password
import com.example.passwordmanager.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLoginBottomSheetContainer(
    saveLogin: (Login) -> Unit,
    encryptPass: (String) -> Password
) {

    var loginType by remember {
        mutableStateOf("")
    }

    var loginUsername by remember {
        mutableStateOf("")
    }

    var loginPassword by remember {
        mutableStateOf("")
    }

    var isValid by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.fontColorGray),
                    unfocusedBorderColor = colorResource(id = R.color.fontColorGray),
                ),
                value = loginType,
                onValueChange = { typed ->
                    loginType = typed
                    isValid = typed.isNotEmpty()

                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                label = {
                    Text(text = "Account Name")
                },
                modifier = Modifier
                    .fillMaxWidth()
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
                value = loginUsername,
                onValueChange = { typed ->
                    loginUsername = typed
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
                value = loginPassword,
                onValueChange = { typed ->
                    loginPassword = typed
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.fontColorGray),
                    unfocusedBorderColor = colorResource(id = R.color.fontColorGray),
                ),
                label = {

                    Text(text = "Password")

                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(25.dp)
            )

            Button(
                onClick = {
                    if (loginType.isNotEmpty() && loginUsername.isNotEmpty() && loginPassword.isNotEmpty()) {

                        val password = encryptPass(loginPassword)
                        saveLogin(
                            Login(
                                id = 0,
                                loginType = loginType,
                                loginUsername = loginUsername,
                                loginPassword = password.password,
                                loginKey = password.key
                            )
                        )

                    }

                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {

                Text(text = "Add New Account")

            }

        }


    }
}
