package com.sopt.now.compose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import kotlinx.coroutines.launch

class SignupActivity : ComponentActivity() {
    private val signupViewModel by viewModels<SignupViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupScreen(signupViewModel)
        }
    }

    @Composable
    fun SignupScreen(viewModel: SignupViewModel) {
        var id by remember { mutableStateOf("") }
        var pw by remember { mutableStateOf("") }
        var nickname by remember { mutableStateOf("") }
        var mbti by remember { mutableStateOf("") }

        val context = LocalContext.current
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()
        val formState by viewModel.isFormValid.observeAsState()

        Scaffold(
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    text = stringResource(R.string.signup),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = stringResource(R.string.id),
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = id,
                    onValueChange = { id = it },
                    label = { Text(stringResource(R.string.input_id)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = stringResource(R.string.pw),
                    fontSize = 20.sp,
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = pw,
                    onValueChange = { pw = it },
                    label = { Text(stringResource(R.string.input_pw)) },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = stringResource(R.string.nickname),
                    fontSize = 20.sp,
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = nickname,
                    onValueChange = { nickname = it },
                    label = { Text(text = stringResource(R.string.input_nickname)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = stringResource(R.string.mbti),
                    fontSize = 20.sp,
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = mbti,
                    onValueChange = { mbti = it.uppercase() },
                    label = { Text(text = stringResource(R.string.input_mbti)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.validateFormData(id, pw, nickname, mbti)
                    }) {
                    Text(stringResource(R.string.btn_signup))
                }
                Spacer(modifier = Modifier.height(30.dp))

            }
        }

        LaunchedEffect(formState) {
            formState?.let {
                if (!it.isValid) {
                    it.errorMsg?.let { msgResId ->
                        val message = context.getString(msgResId)
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(message)
                        }
                    }
                } else {
                    goToLogin(id, pw, nickname, mbti)
                }
            }
        }
    }

    private fun goToLogin(id: String, pw: String, nickname: String, mbti: String) {
        val user = User(id, pw, nickname, mbti)
        val resultIntent = Intent().apply {
            putExtra("user", user)
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    @Preview(showBackground = true)
    @Composable
    fun SignupPreview() {
        NOWSOPTAndroidTheme {
            //  SignupScreen()
        }
    }
}