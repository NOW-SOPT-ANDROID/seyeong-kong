package com.sopt.now.compose.ui.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.sopt.now.compose.data.Friend
import com.sopt.now.compose.data.Profile
import com.sopt.now.compose.data.friendList
import com.sopt.now.compose.data.userProfile

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    val (oddFriends, evenFriends) = friendList.partition { it.name.toInt() % 2 != 0 }

    LazyColumn {
        item {
            UserProfileItem(userProfile)
        }

        stickyHeader {
            Text(
                text = "Odd Friends",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(5.dp),
                color = Color.Black
            )
        }
        items(items = oddFriends) { friend ->
            FriendProfileItem(friend)
        }

        stickyHeader {
            Text(
                text = "Even Friends",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(5.dp),
                color = Color.Black
            )
        }
        items(items = evenFriends) { friend ->
            FriendProfileItem(friend)
        }
    }
}

@Composable
fun UserProfileItem(profile: Profile) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp)
    ) {
        val (profileIcon, profileName, profileDes) = createRefs()

        Icon(
            modifier = Modifier
                .constrainAs(profileIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .size(50.dp),
            imageVector = profile.profileImage,
            contentDescription = null
        )

        Text(
            modifier = Modifier.constrainAs(profileName) {
                top.linkTo(profileIcon.top)
                bottom.linkTo(profileIcon.bottom)
                start.linkTo(profileIcon.end, margin = 5.dp)
            },
            text = profile.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.constrainAs(profileDes) {
                top.linkTo(profileIcon.top)
                bottom.linkTo(profileIcon.bottom)
                start.linkTo(profileName.end, margin = 10.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            text = profile.selfDescription,
            fontSize = 14.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
fun FriendProfileItem(friend: Friend) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    val fadeInAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0.3f,
        animationSpec = tween(durationMillis = 500), label = ""
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .alpha(fadeInAlpha)
    ) {
        val (friendIcon, friendName, friendDes) = createRefs()
        Icon(
            modifier = Modifier
                .constrainAs(friendIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .size(40.dp),
            imageVector = friend.profileImage,
            contentDescription = null
        )

        Text(
            modifier = Modifier.constrainAs(friendName) {
                top.linkTo(friendIcon.top)
                bottom.linkTo(friendIcon.bottom)
                start.linkTo(friendIcon.end, margin = 5.dp)
            },
            text = friend.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.constrainAs(friendDes) {
                top.linkTo(friendIcon.top)
                bottom.linkTo(friendIcon.bottom)
                start.linkTo(friendName.end, margin = 10.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            text = friend.selfDescription,
            fontSize = 12.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
