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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.forwords.R
import com.example.forwords.data.BookModel
import com.example.forwords.data.FullBookModel
import com.example.forwords.data.ReviewModel
import com.example.forwords.network.ApiManager
import com.example.testapp.ui.theme.AnnBack
import com.example.testapp.ui.theme.LightBlue
import com.example.testapp.ui.theme.SelectedTab
import com.example.testapp.ui.theme.SimpleTab
import com.example.testapp.ui.theme.TabBack
import com.example.testapp.ui.theme.ToxicGreen
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookScreen(book_id: Int) {
    val apiService = ApiManager()
    var book by remember { mutableStateOf(FullBookModel(0, "", "", "", 0.0f, "")) }
    apiService.getBookById(book_id) {
        if (it != null)
            book = it
    }

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
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp)
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Fit,
            )
            Column() {
                Text(
                    text = book.name,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
                Text(
                    text = "by ${book.author}",
                    fontSize = 16.sp,
                    modifier = Modifier.padding()
                )
            }
            Text(
                text = book.rate.toString(),
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 10.dp, end = 10.dp)
            )
        }
        AsyncImage(
            model = "https://depravo.pythonanywhere.com/${book.path}",
            contentDescription = "book_img",
            modifier = Modifier
                .padding(10.dp)
                .size(300.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Likes(book.rate)
        BookTabLayout(book)
    }
}

@Composable
fun Likes(rate: Float) {
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
            for (i in 1..5) {
                if (i <= rate.toInt())
                    Image(
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = null
                    )
                else
                    Image(
                        painter = painterResource(id = R.drawable.like_border),
                        contentDescription = null
                    )
            }
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun BookTabLayout(book: FullBookModel) {
    val tabList = listOf("Annotation", "Reviews", "Quotes")
    var tabIndex by remember { mutableStateOf(0) }
    val corountineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(20.dp)),

        ) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = TabBack,
            indicator = {},
            divider = {}
        ) {
            tabList.forEachIndexed { index, text ->
                val selected = tabIndex == index
                Tab(
                    selected = selected,
                    onClick = {
                        tabIndex = index
                    },
                    text = { Text(text = text, color = Color.White) },
                    modifier = if (selected) Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(SelectedTab)
                    else Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(SimpleTab)
                )
            }
        }
        when (tabIndex) {
            0 -> Annotation(book.annotation)
        }
    }
}

@Composable
fun Annotation(annotation: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = AnnBack),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = annotation,
            fontSize = 20.sp,
            modifier = Modifier.padding(20.dp).verticalScroll(rememberScrollState()),
            color = Color.White
        )
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
