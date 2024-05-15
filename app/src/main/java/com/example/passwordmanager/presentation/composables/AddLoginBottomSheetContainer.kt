package com.example.passwordmanager.presentation.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.R
import com.example.passwordmanager.presentation.model.LoginUiInfo


@OptIn(ExperimentalMaterial3Api::class)
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
                value = loginPassword.value,
                onValueChange = {
                    updateLoginPassword(it)
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
                    if(
                        saveLogin(
                            LoginUiInfo(
                                loginType = loginType.value,
                                loginUsername = loginUsername.value,
                                loginPassword = loginPassword.value
                            )
                        )
                    ){
                        Toast
                            .makeText(context, "New Login Details Added!", Toast.LENGTH_LONG)
                            .show()
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
