package com.sopt.now.compose

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.getValue
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SignupActivity : ComponentActivity() {
    companion object {
        const val MIN_ID_LENGTH = 6
        const val MAX_ID_LENGTH = 10
        const val MIN_PW_LENGTH = 8
        const val MAX_PW_LENGTH = 12
        const val MBTI_LENGTH = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupScreen()
        }
    }

    @Composable
    fun SignupScreen() {
        var id by remember { mutableStateOf("") }
        var pw by remember { mutableStateOf("") }
        var nickname by remember { mutableStateOf("") }
        var mbti by remember { mutableStateOf("") }

        val context = LocalContext.current
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()

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
                        if (validateInput(
                                context,
                                id,
                                pw,
                                nickname,
                                mbti,
                                snackbarHostState,
                                coroutineScope
                            )
                        ) {
                            goToLogin(id, pw, nickname, mbti)
                        }
                    }) {
                    Text(stringResource(R.string.btn_signup))
                }
                Spacer(modifier = Modifier.height(30.dp))

            }
        }
    }

    private fun showSnackbar(
        snackbarHostState: SnackbarHostState,
        message: String,
        coroutineScope: CoroutineScope
    ) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }

    private fun validateInput(
        context: Context,
        id: String,
        pw: String,
        nickname: String,
        mbti: String,
        snackbarHostState: SnackbarHostState,
        coroutineScope: CoroutineScope
    ): Boolean {
        return validateId(context, id, snackbarHostState, coroutineScope) &&
                validatePw(context, pw, snackbarHostState, coroutineScope) &&
                validateNickname(context, nickname, snackbarHostState, coroutineScope) &&
                validateMbti(context, mbti, snackbarHostState, coroutineScope)
    }

    private fun validateId(
        context: Context,
        id: String,
        snackbarHostState: SnackbarHostState,
        coroutineScope: CoroutineScope
    ): Boolean {
        return when {
            id.length < MIN_ID_LENGTH -> {
                showSnackbar(
                    snackbarHostState,
                    context.resources.getString(R.string.id_greater_than),
                    coroutineScope
                )
                false
            }

            id.length > MAX_ID_LENGTH -> {
                showSnackbar(
                    snackbarHostState,
                    context.resources.getString(R.string.id_less_than),
                    coroutineScope
                )
                false
            }

            else -> true
        }
    }

    private fun validatePw(
        context: Context,
        pw: String,
        snackbarHostState: SnackbarHostState,
        coroutineScope: CoroutineScope
    ): Boolean {
        return when {
            pw.length < MIN_PW_LENGTH -> {
                showSnackbar(
                    snackbarHostState,
                    context.getString(R.string.pw_greater_than),
                    coroutineScope
                )
                false
            }

            pw.length > MAX_PW_LENGTH -> {
                showSnackbar(
                    snackbarHostState,
                    context.getString(R.string.pw_less_than),
                    coroutineScope
                )
                false
            }

            else -> true
        }
    }

    private fun validateNickname(
        context: Context,
        nickname: String,
        snackbarHostState: SnackbarHostState,
        coroutineScope: CoroutineScope
    ): Boolean {
        if (nickname.isBlank()) {
            showSnackbar(
                snackbarHostState,
                context.getString(R.string.input_nickname),
                coroutineScope
            )
            return false
        }
        return true
    }

    private fun validateMbti(
        context: Context,
        mbti: String,
        snackbarHostState: SnackbarHostState,
        coroutineScope: CoroutineScope
    ): Boolean {
        if (mbti.length != MBTI_LENGTH || mbti.isBlank()) {
            showSnackbar(snackbarHostState, context.getString(R.string.input_mbti), coroutineScope)
            return false
        }
        return true
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
            SignupScreen()
        }
    }
}