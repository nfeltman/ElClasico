package com.dugonggames.elclasico.ImageManipulation

import com.dugonggames.elclasico.floatArrayOfInts
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DownsampleTest{
    @Test
    fun testDownsample(){
        val source = floatArrayOfInts(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25)
        //val source = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        // (3.5, 5.5, 11.5, 13.5, 0, 0, 0, 0, 0)
        val target = floatArrayOfInts(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        val results = downsample(source, 0, 5, 5, target, 0)
        println(target.joinToString())
        assertArrayEquals(target,
                floatArrayOf(4f, 6f, 7.5f, 14f, 16f, 17.5f, 21.5f, 23.5f, 25f,
                        0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f))
        assertEquals(results, DownsampleResults(3, 3, 9))
    }
}