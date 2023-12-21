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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forwords.R
import com.example.forwords.data.BookModel
import com.example.testapp.ui.theme.LightBlue
import com.example.testapp.ui.theme.ToxicGreen
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showBackground = true)
fun UserScreen() {
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
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.profile_img),
                contentDescription = "logo",
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 20.dp)
                    .size(40.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "LOTUS GIBISKUS",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 10.dp, start = 20.dp)
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
        TabLayout()
    }
}

@ExperimentalFoundationApi
@Composable
fun TabLayout() {
    val tabList = listOf("To Read", "Reading", "Completed")
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
        val books1 = listOf<BookModel>(
            BookModel("Book 1", "img1.jpg", "Description 1"),
            BookModel("Book 2", "img2.jpg", "Description 2"),
            BookModel("Book 3", "img3.jpg", "Description 3")
        )

        val books2 = listOf<BookModel>(
            BookModel("Book A", "imgA.jpg", "Description A"),
            BookModel("Book B", "imgB.jpg", "Description B"),
            BookModel("Book C", "imgC.jpg", "Description C")
        )

        val books3 = listOf<BookModel>(
            BookModel("Book X", "imgX.jpg", "Description X"),
            BookModel("Book Y", "imgY.jpg", "Description Y"),
            BookModel("Book Z", "imgZ.jpg", "Description Z")
        )
        when (tabIndex) {
            0 -> BookList(books1)
            1 -> BookList(books2)
            2 -> BookList(books3)
        }
    }
}

@Composable
fun BookList(list: List<BookModel>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(list) { _, item ->
            BookItem(item)
        }
    }
}

@Composable
fun BookItem(item: BookModel) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Text(text = item.title)
            Text(text = item.description)
        }
        Image(painterResource(id = R.drawable.book), contentDescription = "book_img")
    }
}