package com.example.passwordmanager.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.R
import com.example.passwordmanager.data.local.Login
import com.example.passwordmanager.ui.theme.fontFamilyRobotoBold
import com.example.passwordmanager.ui.theme.fontFamilyRobotoLight
import com.example.passwordmanager.utils.Password

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditLoginBottomSheetContainer(
    login: Login,
    deleteLogin: (Login) -> Unit,
    updateLogin: (Login) -> Unit,
    decryptPass: (Password) -> String,
    encryptEditedPass: (String) -> Password
) {

    var loginType by remember {
        mutableStateOf(login.loginType)
    }

    var loginUsername by remember {
        mutableStateOf(login.loginUsername)
    }

//    val decryptedPass= login.loginKey?.let {
//        Password(
//            key = it,
//            password = login.loginPassword
//        )
//    }?.let { decryptPass(it) }

    val decryptedPass = decryptPass(
        Password(
            key = login.loginKey,
            password = login.loginPassword
        )
    )

    var loginPassword by remember {
        mutableStateOf(decryptedPass)
    }
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(35.dp, 0.dp, 0.dp, 0.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Account Details",
                fontFamily = fontFamilyRobotoBold,
                color = colorResource(id = R.color.accountDetailsHeadingColor),
                fontSize = 25.sp
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                textStyle = TextStyle(
                    fontFamily = fontFamilyRobotoBold,
                    fontSize = 20.sp

                ),
                value = loginType,
                onValueChange = { typed ->
                    loginType = typed
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    containerColor = Color.White
                ),
                label = {
                    Text(
                        text = "Account Name",
                        fontFamily = fontFamilyRobotoLight,
                        fontWeight = FontWeight.Bold
                    )

                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(25.dp)
            )

            OutlinedTextField(
                textStyle = TextStyle(
                    fontFamily = fontFamilyRobotoBold,
                    fontSize = 20.sp

                ),
                value = loginUsername,
                onValueChange = { typed ->
                    loginUsername = typed
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    containerColor = Color.White
                ),
                label = {
                    Text(
                        text = "Username/ Email",
                        fontFamily = fontFamilyRobotoLight,
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .height(25.dp)
            )

            loginPassword?.let {
                OutlinedTextField(
                    textStyle = TextStyle(
                        fontFamily = fontFamilyRobotoBold,
                        fontSize = 20.sp

                    ),
                    value = it,
                    onValueChange = { typed ->
                        loginPassword = typed
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        containerColor = Color.White
                    ),
                    label = {
                        Text(
                            text = "Password",
                            fontFamily = fontFamilyRobotoLight,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Spacer(
                modifier = Modifier
                    .height(25.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    onClick = {
                        if (loginType.isNotEmpty() && loginUsername.isNotEmpty() && loginPassword.isNotEmpty()) {

                            if (loginPassword != decryptedPass) {
                                val password = encryptEditedPass(loginPassword)
                                updateLogin(
                                    Login(
                                        id = login.id,
                                        loginType = loginType,
                                        loginUsername = loginUsername,
                                        loginPassword = password.password,
                                        loginKey = password.key
                                    )
                                )
                            }
                            updateLogin(
                                Login(
                                    id = login.id,
                                    loginType = loginType,
                                    loginUsername = loginUsername,
                                    loginPassword = loginPassword,
                                    loginKey = login.loginKey
                                )
                            )

                        }


                    },
                    modifier = Modifier
                        .weight(1f)
                ) {

                    Text(text = "Edit")

                }
                Spacer(
                    modifier = Modifier
                        .width(10.dp)
                )

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    onClick = {
                        deleteLogin(login)

                    },
                    modifier = Modifier
                        .weight(1f)
                ) {

                    Text(text = "Delete")

                }

            }


        }


    }
}
