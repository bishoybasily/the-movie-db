package com.neugelb.themoviedb

import android.net.ConnectivityManager
import com.neugelb.themoviedb.di.ComponentMainTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @field:[Inject]
    lateinit var connectivityManager: ConnectivityManager

    @Before
    fun before() {
        ComponentMainTest.initialize().inject(this)
    }


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        assertNotNull(connectivityManager)
    }

}
