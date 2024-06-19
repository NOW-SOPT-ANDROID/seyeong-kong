package com.sopt.now.compose.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sopt.now.compose.R
import com.sopt.now.compose.network.request.RequestLoginDto
import com.sopt.now.compose.util.noRippleClickable

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = hiltViewModel()
    val snackbarHostState = remember { SnackbarHostState() }

    var inputId by remember { mutableStateOf("") }
    var inputPw by remember { mutableStateOf("") }

    val authState by viewModel.loginStatus.observeAsState()

    LaunchedEffect(authState) {
        authState?.let { state ->
            if (state.isSuccess) {
                snackbarHostState.showSnackbar(
                    message = "로그인 성공! 홈 화면으로 이동합니다.",
                    duration = SnackbarDuration.Short
                )
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            } else {
                snackbarHostState.showSnackbar(
                    message = state.message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 30.sp,
            )

            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = inputId,
                onValueChange = { inputId = it },
                label = { Text(stringResource(R.string.id)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = inputPw,
                onValueChange = { inputPw = it },
                label = { Text(stringResource(R.string.pw)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                modifier = Modifier.fillMaxWidth()
            )

            TextButton(
                onClick = { navController.navigate("signup") },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(
                    stringResource(R.string.signup_kr),
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            CustomBtn(
                text = stringResource(R.string.btn_login),
                onClick = {
                    viewModel.login(RequestLoginDto(inputId, inputPw))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )

        }
    }
}

@Composable
fun CustomBtn(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(30.dp)
            )
            .noRippleClickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}