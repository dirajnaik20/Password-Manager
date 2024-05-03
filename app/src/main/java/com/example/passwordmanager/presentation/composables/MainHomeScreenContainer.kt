package com.example.passwordmanager.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.passwordmanager.R
import com.example.passwordmanager.data.local.Login
import com.example.passwordmanager.ui.theme.fontFamilyRobotoBold

@Composable
fun MainHomeScreenContainer(
    getSavedLoginList: () -> LiveData<List<Login>>,
    openEditBottomSheet: (Login) -> Unit
) {

    val savedList by getSavedLoginList().observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.mainScreenBackground))
            .padding(10.dp, 120.dp, 10.dp, 10.dp)
    ) {
        savedList?.let { LazyColumnContainer(it, openEditBottomSheet) }

    }

}

@Composable
fun LazyColumnContainer(
    savedLoginList: List<Login>,
    openEditBottomSheet: (Login) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn {
            items(savedLoginList.size) { index ->

                SavedLoginListItem(
                    savedLoginList[index],
                    openEditBottomSheet = openEditBottomSheet
                )
                Spacer(
                    modifier = Modifier
                        .height(15.dp)
                )

            }
        }


    }
}

@Composable
fun SavedLoginListItem(
    login: Login,
    openEditBottomSheet: (Login) -> Unit
) {

    ElevatedCard(
        Modifier
            .height(100.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(50.dp))
        ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 50.dp
        ),

        ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                Text(
                    text = login.loginType,
                    fontFamily = fontFamilyRobotoBold,
                    fontSize = 20.sp
                )
                Spacer(
                    modifier = Modifier
                        .width(10.dp)
                )
                Text(
                    text = "*********",
                    fontFamily = fontFamilyRobotoBold,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.fontColorGray)
                )
            }

            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(CircleShape)
                    .size(45.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        openEditBottomSheet(login)

                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
        }

    }

}
