package com.example.fakestoreapi

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.fakestoreapi.data.local.ProductDao
import com.example.fakestoreapi.data.local.ProductDataBase
import com.example.fakestoreapi.data.local.ProductEntity
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class RoomDataBaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var productDao: ProductDao
    private lateinit var db: ProductDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ProductDataBase::class.java).build()
        productDao = db.getProductDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertProduct_empty() = runBlocking {
        // Given
        val productList = listOf<ProductEntity>()

        // When
        productDao.insertAllProducts(productList)

        // Then A
        val it = productDao.getAllProducts().getOrAwaitValue()
        assertThat(it).isNotNull()
        assertThat(it).isEmpty()

        // Then B
        productDao.getAllProducts().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertProduct_happyCase_1element() = runBlocking {
        // Given
        val productList = listOf(ProductEntity(1,"Hola", 2.0,"Bye","Mejor Producto","example.com", 0.0,0))

        // When
        productDao.insertAllProducts(productList)

        // Then
        productDao.getAllProducts().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertProducts_happyCase_3elements() = runBlocking {
        // Given
        val productList = listOf(
            ProductEntity(1,"Hola", 2.0,"Bye","Mejor Producto","example.com", 0.0,0),
            ProductEntity(2,"Hola", 4.0,"Bye","Mejor Producto","example.com", 0.0,0),
            ProductEntity(3,"Hola", 6.0,"Bye","Mejor Producto","example.com", 0.0,0))

        // When
        productDao.insertAllProducts(productList)

        // Then
        productDao.getAllProducts().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}