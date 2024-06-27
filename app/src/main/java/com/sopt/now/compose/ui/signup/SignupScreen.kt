package com.sopt.now.compose.ui.signup

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sopt.now.compose.R
import com.sopt.now.compose.util.noRippleClickable
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignupRoute(navController: NavController, viewModel: SignupViewModel = hiltViewModel()) {
    val snackbarHostState = remember { SnackbarHostState() }
    val signUpState by viewModel.signUpState.collectAsState()
    val authState by viewModel.signupStatus.observeAsState(null)

    LaunchedEffect(authState) {
        authState?.let { state ->
            if (state.message.isNotEmpty()) {
                snackbarHostState.showSnackbar(
                    message = state.message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is SignupViewModel.SignupSideEffect.ShowError -> {
                    snackbarHostState.showSnackbar(
                        message = sideEffect.message,
                        duration = SnackbarDuration.Short
                    )
                }

                SignupViewModel.SignupSideEffect.NavigateToMain -> {
                    navController.navigate("login") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            }
        }
    }

    SignupScreen(
        signUpState = signUpState,
        onIdChange = { viewModel.updateSignUpState(id = it) },
        onPasswordChange = { viewModel.updateSignUpState(password = it) },
        onNicknameChange = { viewModel.updateSignUpState(nickname = it) },
        onPhoneChange = { viewModel.updateSignUpState(phone = it) },
        onSignUpClick = { viewModel.signUp() },
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun SignupScreen(
    signUpState: SignUpState,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    snackbarHostState: SnackbarHostState
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {}
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.signup),
                fontSize = 30.sp,
            )

            Spacer(Modifier.height(20.dp))
            OutlinedTextField(
                value = signUpState.id,
                onValueChange = onIdChange,
                label = { Text(stringResource(R.string.id)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = signUpState.password,
                onValueChange = onPasswordChange,
                label = { Text(stringResource(R.string.pw)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = signUpState.nickname,
                onValueChange = onNicknameChange,
                label = { Text(stringResource(R.string.nickname)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = signUpState.phone,
                onValueChange = onPhoneChange,
                label = { Text(stringResource(R.string.phone)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))
            CustomBtn(
                text = stringResource(R.string.signup_kr),
                onClick = onSignUpClick,
                modifier = Modifier.fillMaxWidth()
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