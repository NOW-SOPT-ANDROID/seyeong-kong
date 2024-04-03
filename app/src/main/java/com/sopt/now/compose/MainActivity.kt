package com.sopt.now.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val receivedId = intent.getStringExtra("id") ?: ""
            val receivedNickname = intent.getStringExtra("nickname") ?: ""
            val receivedMbti = intent.getStringExtra("mbti") ?: ""
            MainScreen(receivedId, receivedNickname, receivedMbti)
        }
    }
}

@Composable
fun MainScreen(receivedId: String = "", receivedNickname: String = "", receivedMbti: String = "") {
    val id by remember { mutableStateOf(receivedId) }
    val nickname by remember { mutableStateOf(receivedNickname) }
    val mbti by remember { mutableStateOf(receivedMbti) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = stringResource(R.string.img),
            modifier = Modifier
                .aspectRatio(1f)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = nickname ,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = mbti,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = id,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    NOWSOPTAndroidTheme {
        MainScreen()
    }
}