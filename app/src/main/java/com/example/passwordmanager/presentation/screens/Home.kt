package com.example.passwordmanager.presentation.screens

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import com.example.passwordmanager.R
import com.example.passwordmanager.data.local.Login
import com.example.passwordmanager.presentation.composables.AddLoginBottomSheetContainer
import com.example.passwordmanager.presentation.composables.EditLoginBottomSheetContainer
import com.example.passwordmanager.presentation.composables.MainHomeScreenContainer
import com.example.passwordmanager.presentation.composables.TopBarHeading
import com.example.passwordmanager.utils.EncryptionUtils
import com.example.passwordmanager.utils.Password

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navHostController: NavHostController,
    saveLogin: (Login) -> Unit,
    getSavedLoginList: () -> LiveData<List<Login>>,
    deleteLogin: (Login) -> Unit,
    updateLogin: (Login) -> Unit,
    encryptPass: (String) -> Password,
    decryptPass: (Password) -> String,
    encryptEditedPass: (String) -> Password

) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var isShowAddBottomSheet by remember { mutableStateOf(false) }
    var editLoginItem by remember {
        mutableStateOf(
            Login(
                id = 0,
                loginType = "",
                loginUsername = "",
                loginPassword = "",
                loginKey = EncryptionUtils.generateAESKey()

            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarHeading()
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.mainScreenBackground)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isShowAddBottomSheet = true
                    showBottomSheet = true

                },
                shape = RoundedCornerShape(5.dp),
                containerColor = colorResource(id = R.color.floatingButton)
            ) {
                Icon(
                    modifier = Modifier
                        .size(50.dp)
                    ,
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                    tint = Color.White
                )

            }
        }
    ) { contentPadding ->
        // Screen content

        MainHomeScreenContainer(
            getSavedLoginList = getSavedLoginList,
            openEditBottomSheet = {
                editLoginItem = it
                isShowAddBottomSheet = false
                showBottomSheet = true

            }
        )

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {

                when (isShowAddBottomSheet) {
                    true -> AddLoginBottomSheetContainer(
                        saveLogin = saveLogin,
                        encryptPass = encryptPass
                    )

                    false -> EditLoginBottomSheetContainer(
                        editLoginItem,
                        deleteLogin = {
                            deleteLogin(it)
                        },
                        updateLogin = {
                            updateLogin(it)
                        },
                        decryptPass = {
                            decryptPass(it)
                        },
                        encryptEditedPass = {
                            encryptEditedPass(it)

                        }
                    )
                }

            }
        }
    }
}



