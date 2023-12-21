package com.example.forwords.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forwords.R
import com.example.forwords.data.ReviewModel
import com.example.testapp.ui.theme.LightBlue
import com.example.testapp.ui.theme.ToxicGreen
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground =  true)
@Composable
fun BookScreen() {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "background_image",
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.5f),
        contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_img),
                contentDescription = "logo",
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp)
                    .size(40.dp),
                contentScale = ContentScale.Crop
            )
            Column() {
                Text(
                    text = "Harry Maquire",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
                Text(
                    text = "by Man United",
                    fontSize = 16.sp,
                    modifier = Modifier.padding()
                )
            }
            Text(
                text = "3.0",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 10.dp, end = 10.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.profile_img),
            contentDescription = "profile",
            modifier = Modifier
                .padding(10.dp)
                .size(100.dp)
                .clip(CircleShape),
        )
        Likes()
        BookTabLayout()
    }
}

@Composable
fun Likes() {
    Box(
        modifier = Modifier
            .padding(top = 20.dp, bottom = 20.dp, start = 40.dp, end = 40.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color.Gray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(painter = painterResource(id = R.drawable.like), contentDescription = null)
            Image(painter = painterResource(id = R.drawable.like), contentDescription = null)
            Image(painter = painterResource(id = R.drawable.like), contentDescription = null)
            Image(painter = painterResource(id = R.drawable.like_border), contentDescription = null)
            Image(painter = painterResource(id = R.drawable.like_border), contentDescription = null)
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun BookTabLayout() {
    val tabList = listOf("Reviews", "Collections", "Quotes")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val corountineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(20.dp)),

        ) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = LightBlue,
            contentColor = Color.White
        ) {
            tabList.forEachIndexed { index, text ->
                val selected = tabIndex == index
                Tab(
                    selected = selected,
                    onClick = {
                        corountineScope.launch { pagerState.animateScrollToPage(index) }
                    },
                    text = { Text(text = text, color = Color.Black) },
                    modifier = if (selected) Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(ToxicGreen)
                    else Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color.White)
                )
            }
        }
        val reviews1 = listOf<ReviewModel>(
            ReviewModel("Mike Penya", "Good book", 5.0f),
            ReviewModel("Mike Menyan", "Bad book", 3.0f),
            ReviewModel("Robin Kul", "Norm book", 4.0f),

            )
        List(reviews1)
    }
}

@Composable
fun List(list: List<ReviewModel>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(list) { _, item ->
            ReviewItem(item)
        }
    }
}

@Composable
fun ReviewItem(item: ReviewModel) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .clip(
                RoundedCornerShape(20.dp)
            )
            .background(LightBlue)

    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = item.book_name, modifier = Modifier.padding(bottom = 10.dp))
            Text(text = item.review, modifier = Modifier.padding(bottom = 10.dp))
            Text(text = "Rate: ${item.rate}", modifier = Modifier.padding(bottom = 10.dp))
        }
    }
}
