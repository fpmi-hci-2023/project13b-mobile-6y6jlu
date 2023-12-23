package com.example.forwords.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.forwords.R
import com.example.forwords.data.BookModel
import com.example.forwords.network.ApiManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToBook: (book_id: Int) -> Unit) {
    val search = remember {
        mutableStateOf("")
    }
    val apiService = ApiManager()
    val bookList = remember { mutableStateListOf<BookModel>() }
    apiService.getAllBooks {
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
    SearchBar(
        modifier = Modifier
            .alpha(0.9f)
            .padding(15.dp)
            .clip(RoundedCornerShape(20.dp)),
        query = search.value,
        onQueryChange = { newQuery -> search.value = newQuery },
        onSearch = {
            apiService.getBookByName(search.value) {
                if (it != null) {
                    bookList.clear()
                    bookList.addAll(it)
                }
            }
        },
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        },
        trailingIcon = {},
        active = true,
        onActiveChange = {},
        tonalElevation = 0.dp
    ) {
        BookList(list = bookList.toList(), onNavigateToBook)
    }
}


@Composable
fun BookList(list: List<BookModel>, onNavigateToBook: (book_id: Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        itemsIndexed(list) { _, item ->
            BookItem(item, onNavigateToBook)
        }
    }
}

@Composable
fun BookItem(item: BookModel, onNavigateToBook: (book_id: Int) -> Unit) {
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
            Text(text = item.name, modifier = Modifier.width(200.dp))
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