package com.sopt.now.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch


class LoginActivity : ComponentActivity() {
    private lateinit var signupActivityResultLauncher: ActivityResultLauncher<Intent>
    private var user: User? = null
    private lateinit var snackbarHostState: SnackbarHostState

    enum class ScreenState {
        LOGIN,
        MAIN
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        snackbarHostState = SnackbarHostState()
        setupActivityResultLauncher()
        setContent {
            Content(user)
        }
    }

    private fun setupActivityResultLauncher() {
        signupActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                user = data?.getSerializableExtraProvider("user") as? User
                setContent {
                    Content(user)
                }
            }
        }
    }

    @Composable
    fun Content(user: User?) {
        val screenState = remember { mutableStateOf(ScreenState.LOGIN) }

        when (screenState.value) {
            ScreenState.LOGIN -> LoginScreen(
                user = user,
                onLoginSuccess = {
                    screenState.value = ScreenState.MAIN
                },
                onSignupClicked = {
                    val signupIntent = Intent(this@LoginActivity, SignupActivity::class.java)
                    signupActivityResultLauncher.launch(signupIntent)
                }
            )

            ScreenState.MAIN -> {
                navigateToMainScreen()
            }
        }
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java).apply { putExtra("user", user) }
        startActivity(intent)
        finish()
    }
}

@Composable
fun LoginScreen(
    user: User?,
    onLoginSuccess: (User) -> Unit,
    onSignupClicked: () -> Unit
) {
    var id by remember { mutableStateOf(user?.id ?: "") }
    var pw by remember { mutableStateOf(user?.pw ?: "") }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

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

            TextButton(onClick = onSignupClicked) {
                Text(stringResource(R.string.login_to_signup))
            }

            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (id == user?.id && pw == user.pw) {
                        onLoginSuccess(user)
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = context.getString(R.string.fail_login),
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                }
            ) {
                Text(stringResource(R.string.btn_login))
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    NOWSOPTAndroidTheme {
        LoginScreen(user = null, onLoginSuccess = { _ -> }, onSignupClicked = {})
    }
}