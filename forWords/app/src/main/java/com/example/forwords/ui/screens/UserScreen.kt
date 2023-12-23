package com.example.forwords.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.forwords.R
import com.example.forwords.data.BookModel
import com.example.forwords.data.UserBookModel
import com.example.forwords.data.UserModel
import com.example.forwords.network.ApiManager
import com.example.forwords.util.DataStoreManager
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
fun UserScreen(onNavigateToBook: (book_id: Int) -> Unit, context: Context) {
    val dataStoreManager = DataStoreManager(context)
    val apiService = ApiManager()
    val user_id = dataStoreManager.getUserId().collectAsState(initial = 1)
    val user = remember { mutableStateOf(UserModel("", "")) }
    apiService.getUserById(user_id.value) {
        if (it != null) {
            user.value = it
        }
    }
    val bookList = remember { mutableStateListOf<UserBookModel>() }
    apiService.getUserBooks(user_id.value) {
        if (it != null) {
            bookList.clear()
            bookList.addAll(it)
        }
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
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 20.dp)
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Fit
            )
            Text(
                text = user.value.name,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 40.dp, start = 40.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.profile_img),
            contentDescription = "profile",
            modifier = Modifier
                .padding(10.dp)
                .size(200.dp)
                .clip(CircleShape),
        )
        TabLayout(bookList, onNavigateToBook)
    }
}

@ExperimentalFoundationApi
@Composable
fun TabLayout(bookList: MutableList<UserBookModel>, onNavigateToBook: (book_id: Int) -> Unit) {
    val tabList = listOf("To Read", "Reading", "Completed")
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

        val books = remember {
            mutableStateOf(listOf<UserBookModel>())
        }

        when (tabIndex) {
            0 -> {
                books.value =  bookList.filter { it -> it.status == "Буду читать" }
                UserBookList(list = books.value, onNavigateToBook = onNavigateToBook)
            }

            1 -> {
                books.value = bookList.filter { it -> it.status == "Читаю сейчас" }
                UserBookList(list = books.value, onNavigateToBook = onNavigateToBook)
            }

            2 -> {
                books.value = bookList.filter { it -> it.status == "Прочитано" }
                UserBookList(list = books.value, onNavigateToBook = onNavigateToBook)
            }
        }
    }
}

@Composable
fun UserBookList(list: List<UserBookModel>, onNavigateToBook: (book_id: Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        itemsIndexed(list) { _, item ->
            UserBookItem(item, onNavigateToBook)
        }
    }
}

@Composable
fun UserBookItem(item: UserBookModel, onNavigateToBook: (book_id: Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
            .clickable {
                onNavigateToBook(item.book_id)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.padding(top = 8.dp, start = 8.dp)
        ) {
            Text(text = item.name)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = item.author)
        }
        AsyncImage(
            model = "https://depravo.pythonanywhere.com/${item.path}",
            contentDescription = "book_img",
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Fit
        )
    }
}