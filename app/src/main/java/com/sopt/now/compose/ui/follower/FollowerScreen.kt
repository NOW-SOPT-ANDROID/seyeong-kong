//package com.sopt.now.compose.ui.follower
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import androidx.lifecycle.viewmodel.compose.viewModel
//import coil.compose.AsyncImage
//import com.sopt.now.compose.network.reponse.ResponseFollowerDto
//
//@Composable
//fun FollowerScreen() {
//    val viewModel: FollowerViewModel = viewModel()
//    val followers by viewModel.followers.collectAsStateWithLifecycle()
//
//    LazyColumn {
//        items(followers) { follower ->
//            FollowerItem(follower)
//        }
//    }
//}
//
//@Composable
//fun FollowerItem(follower: ResponseFollowerDto.Data) {
//    Card(
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxSize()
//    ) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            AsyncImage(
//                model = follower.avatar,
//                contentDescription = "Profile Picture",
//                modifier = Modifier
//                    .size(150.dp)
//                    .padding(8.dp)
//            )
//            Column {
//                Text("${follower.firstName} ${follower.lastName}", color = Color.Black)
//                Text(follower.email, color = Color.Gray)
//            }
//        }
//    }
//}