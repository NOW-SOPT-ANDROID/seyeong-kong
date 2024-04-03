package com.sopt.now.compose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val receivedId = intent.getStringExtra("id") ?: ""
            val receivedPw = intent.getStringExtra("pw") ?: ""
            val receivedNickname = intent.getStringExtra("nickname") ?: ""
            val receivedMbti = intent.getStringExtra("mbti") ?: ""
            LoginScreen(receivedId, receivedPw, receivedNickname, receivedMbti)
        }
    }
}

@Composable
fun LoginScreen(receivedId: String = "", receivedPw: String = "", receivedNickname: String = "", receivedMbti: String = "") {
    var id by remember { mutableStateOf(receivedId) }
    var pw by remember { mutableStateOf(receivedPw) }
    val nickname by remember { mutableStateOf(receivedNickname) }
    val mbti by remember { mutableStateOf(receivedMbti) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Text(
            text = stringResource(R.string.login_sopt),
            fontSize = 30.sp,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height((30.dp)))
        Text(
            text = stringResource(R.string.id),
            fontSize = 20.sp
        )

        TextField(
            value = id,
            onValueChange = { id = it },
            label = { Text(stringResource(R.string.input_id)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height((30.dp)))
        Text(
            text = stringResource(R.string.pw),
            fontSize = 20.sp
        )

        TextField(
            value = pw,
            onValueChange = { pw = it },
            label = { Text(stringResource(R.string.input_pw)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        TextButton(onClick = {
            val intent = Intent(context, SignupActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(stringResource(R.string.login_to_signup))
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (id == receivedId && pw == receivedPw) {
                    Toast.makeText(context, R.string.success_login, Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MainActivity::class.java).apply {
                        putExtra("id", id)
                        putExtra("nickname", nickname)
                        putExtra("mbti", mbti)
                    }
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, R.string.fail_login, Toast.LENGTH_SHORT).show()
                }
            }) {
            Text(stringResource(R.string.btn_login))
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    NOWSOPTAndroidTheme {
        LoginScreen()
    }
}