package com.sopt.now.compose.ui.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.sopt.now.compose.R
import com.sopt.now.compose.data.UserRepository

@Composable
fun MypageScreen(navController: NavController) {
    val context = LocalContext.current
    val userRepository = UserRepository(context)
    val user = userRepository.getUserData()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
        ) {
            val (userImg, userNickname, userMbti, userId, logout) = createRefs()

            user?.let { user ->
                Image(
                    modifier = Modifier
                        .constrainAs(userImg) {
                            top.linkTo(parent.top, margin = 70.dp)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        }
                        .size(130.dp),
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.img)
                )

                Text(
                    modifier = Modifier.constrainAs(userNickname) {
                        top.linkTo(userImg.bottom)
                        start.linkTo(userImg.start)
                        end.linkTo(userImg.end)
                    },
                    text = user.nickname,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.constrainAs(userMbti) {
                        top.linkTo(userNickname.top)
                        bottom.linkTo(userNickname.bottom)
                        start.linkTo(userNickname.end, margin = 7.dp)
                    },
                    text = user.mbti,
                    fontSize = 20.sp,
                )

                Text(
                    modifier = Modifier.constrainAs(userId) {
                        top.linkTo(userNickname.bottom, margin = 5.dp)
                        start.linkTo(userNickname.start)
                        end.linkTo(userNickname.end)
                    },
                    text = user.id,
                    fontSize = 20.sp
                )

                TextButton(
                    modifier = Modifier.constrainAs(logout) {
                        top.linkTo(userId.bottom, margin = 10.dp)
                        start.linkTo(userId.start)
                        end.linkTo(userId.end)
                    },
                    onClick = {
                        userRepository.clearUserData()
                        navController.navigate("login")
                        {
                            popUpTo("mypage") { inclusive = true }
                        }
                    },
                ) {
                    Text(
                        text = stringResource(R.string.btn_logout),
                        fontSize = 20.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}