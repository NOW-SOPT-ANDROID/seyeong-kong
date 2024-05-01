package com.sopt.now.compose.ui.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sopt.now.compose.R

@Composable
fun SearchScreen() {
    Text(
        text = stringResource(R.string.search),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}