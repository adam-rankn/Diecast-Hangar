package com.example.diecasthangar

import com.example.diecasthangar.data.model.Model
import org.junit.Test
import kotlin.test.assertEquals

class TestModel {


    @Test
    fun testModel(){

        val newModel = Model(
            manufacturer = "NG",
            scale = "1:400",
            frame = "787",
            airline = "delta",
            photos = arrayListOf(),
            comment = "hello",
            id = null
        )

        assertEquals("NG",newModel.mould)
        assertEquals("regular livery", newModel.livery)
        assertEquals("delta", newModel.airline)
        assertEquals("787", newModel.frame)
        assertEquals("hello", newModel.comment)
        assertEquals(null, newModel.id)
        assertEquals(0, newModel.price)

    }
}