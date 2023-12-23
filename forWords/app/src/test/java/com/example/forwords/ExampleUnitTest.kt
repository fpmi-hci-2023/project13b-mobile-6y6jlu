package com.example.forwords

import com.example.forwords.data.BookModel
import com.example.forwords.data.UserModel
import com.example.forwords.network.ApiManager
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val apiService = ApiManager()
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getting_bookByName_isCorrect() {
        val book =  BookModel(1, "Crooked Kingdom", "Crooked Kingdom is a fantasy novel by American author Leigh Bardugo, published by Henry Holt and Co. in 2016.[1] Set in a world loosely inspired by 19th-century Europe,[2] it takes place days after the events of the duologys first book, Six of Crows.[3] The plot is told from the third-person viewpoints of eight characters.","images/cr.jpg")
        apiService.getBookByName("Crooked") {
            assertEquals(book, it)
        }
    }

    @Test
    fun gettiing_userById_isCorrect() {
        val user = UserModel("Lucy","Snow land on top")
        apiService.getUserById(4) {
            assertEquals(user, it)
        }
    }
}