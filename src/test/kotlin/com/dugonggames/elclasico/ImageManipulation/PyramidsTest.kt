package com.dugonggames.elclasico.ImageManipulation

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class PyramidsTest {

    @Test
    fun testGaussianPyramid() {
        val image = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val pyramid = createGaussianPyramid(image, 4, 4)

        val expectedData = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 3, 5, 11, 13, 8)
        val expectedLevels = listOf(
                PyramidLevel(0, 4, 4),
                PyramidLevel(16, 2, 2),
                PyramidLevel(20, 1, 1)
                )
        assertArrayEquals(expectedData, pyramid.data)
        assertEquals(3, pyramid.numLevels)
        assertEquals(expectedLevels, pyramid.levels)
    }

    @Test
    fun testLaplacianPyramid() {
        val image = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val pyramid = createLaplacianPyramid(image, 4, 4)
        println(pyramid.data.joinToString())

        // TODO: fill in some asserts here

        //1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
        //3, 5, 11, 13
        //8

        /*
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            13, 14, 15, 16

            3, 5,
            11, 13
         */
    }
}