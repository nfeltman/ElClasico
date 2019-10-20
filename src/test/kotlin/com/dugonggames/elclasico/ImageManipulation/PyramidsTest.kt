package com.dugonggames.elclasico.ImageManipulation

import com.dugonggames.elclasico.floatArrayOfInts
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class PyramidsTest {

    @Test
    fun testGaussianPyramid() {
        val image = floatArrayOfInts(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16)
        val pyramid = createGaussianPyramid(image, 4, 4)

        val expectedData = floatArrayOf(
                1f, 2f, 3f, 4f,
                5f, 6f, 7f, 8f,
                9f, 10f, 11f, 12f,
                13f, 14f, 15f, 16f,
                3.5f, 5.5f,
                11.5f, 13.5f,
                8.5f)
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
        val image = floatArrayOfInts(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val pyramid = createLaplacianPyramid(image, 4, 4)
        val expected = floatArrayOf(
                -2.5f, -1.5f, -2.5f, -1.5f,
                1.5f, 2.5f, 1.5f, 2.5f,
                -2.5f, -1.5f, -2.5f, -1.5f,
                1.5f, 2.5f, 1.5f, 2.5f,
                -5f, -3f,
                3f, 5f,
                8.5f)
        assertArrayEquals(expected, pyramid.data)

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