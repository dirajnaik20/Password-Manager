package com.example.passwordmanager.presentation.composables

import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.R
import com.example.passwordmanager.data.local.Login
import com.example.passwordmanager.presentation.model.LoginUiInfo
import com.example.passwordmanager.ui.theme.fontFamilyRobotoBold
import com.example.passwordmanager.ui.theme.fontFamilyRobotoLight
import com.example.passwordmanager.utils.Password

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditLoginBottomSheetContainer(
    login: Login,
    deleteLogin: (Login) -> Unit,
    getDecryptedPassword: (Password) -> String,
    updateEditedLoginType: (String) -> Unit,
    updateEditedLoginUsername: (String) -> Unit,
    updateEditedLoginPassword: (String) -> Unit,
    updateEditedLoginDetails: (
        Login,
        LoginUiInfo
    ) -> Boolean,
    getLoginTypeToEdit: (String) -> MutableState<String>,
    getLoginUsernameToEdit: (String) -> MutableState<String>,
    getDecryptedLoginPasswordToEdit: (Password) -> MutableState<String>,
) {

    val loginType by remember {
        mutableStateOf(getLoginTypeToEdit(login.loginType))
    }

    val loginUsername by remember {
        mutableStateOf(getLoginUsernameToEdit(login.loginUsername))
    }

    val loginPassword by remember {
        mutableStateOf(
            getDecryptedLoginPasswordToEdit(
                Password(
                    key = login.loginKey,
                    password = login.loginPassword
                )
            )
        )
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 35.dp),
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
                value = loginType.value,
                onValueChange = {
                    updateEditedLoginType(it)
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
                value = loginUsername.value,
                onValueChange = {
                    updateEditedLoginUsername(it)
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

            OutlinedTextField(
                textStyle = TextStyle(
                    fontFamily = fontFamilyRobotoBold,
                    fontSize = 20.sp

                ),
                value = loginPassword.value,
                onValueChange = {
                    updateEditedLoginPassword(it)
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

                        if (updateEditedLoginDetails(
                                login,
                                LoginUiInfo(
                                    loginType = loginType.value,
                                    loginUsername = loginUsername.value,
                                    loginPassword = loginPassword.value
                                )
                            )
                        ) {
                            Toast
                                .makeText(context, "Login Details Updated!", Toast.LENGTH_LONG)
                                .show()
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
