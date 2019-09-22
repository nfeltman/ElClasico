package com.dugonggames.elclasico.ImageManipulation

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DownsampleTest{
    @Test
    fun testDownsample(){
        val source = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25)
        //val source = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        // (3.5, 5.5, 11.5, 13.5, 0, 0, 0, 0, 0)
        val target = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        val results = downsample(source, 0, 5, 5, target, 0)
        println(target.joinToString())
        assertArrayEquals(target, intArrayOf(4, 6, 7, 14, 16, 17, 21, 23, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
        assertEquals(results, DownsampleResults(3, 3, 9))
    }
}