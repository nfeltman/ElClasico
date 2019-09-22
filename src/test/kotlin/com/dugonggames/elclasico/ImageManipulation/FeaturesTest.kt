package com.dugonggames.elclasico.ImageManipulation

import com.dugonggames.elclasico.Classifiers.DifferentLengthsException
import com.dugonggames.elclasico.Classifiers.FeatureVector
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.lang.IndexOutOfBoundsException
import java.lang.Math.sqrt

internal class FeatureVectorTest{
    @Test
    fun testFeatureVector(){
        val a = FeatureVector(floatArrayOf(1f, 2f, 3f))
        val b = FeatureVector(floatArrayOf(2f, 3f, 4f))

        assertEquals(a[2], 3f)
        assertEquals(a+b, FeatureVector(floatArrayOf(3f, 5f, 7f)))
        assertEquals(b/2, FeatureVector(floatArrayOf(1f, 1.5f, 2f)))
        assertEquals(a.eDistance(b), sqrt(3.0))
        assertEquals(a.tDistance(b), 3.0)
    }

    @Test
    fun testDifferentLengths(){
        val a = FeatureVector(floatArrayOf(1f, 2f, 3f))
        val b = FeatureVector(floatArrayOf(1f, 2f, 3f, 4f))
        assertThrows(DifferentLengthsException::class.java){a+b}
        assertThrows(DifferentLengthsException::class.java){b+a}
        assertThrows(DifferentLengthsException::class.java){a-b}
        assertThrows(DifferentLengthsException::class.java){b-a}
        assertThrows(DifferentLengthsException::class.java){a.eDistance(b)}
        assertThrows(DifferentLengthsException::class.java){b.eDistance(a)}
        assertThrows(DifferentLengthsException::class.java){a.tDistance(b)}
        assertThrows(DifferentLengthsException::class.java){b.tDistance(a)}
    }
}