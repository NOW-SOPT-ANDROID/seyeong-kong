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
            val user = intent.getSerializableExtraProvider("user") as? User
            MainScreen(user = user ?: User("", "", "", ""))
        }
    }
}

@Composable
fun MainScreen(user: User) {
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
                text = user.nickname,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = user.mbti,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = user.id,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    NOWSOPTAndroidTheme {
        MainScreen(User("test", "test", "test", "test"))
    }
}