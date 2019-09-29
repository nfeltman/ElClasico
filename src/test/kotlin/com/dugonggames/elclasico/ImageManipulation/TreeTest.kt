package com.dugonggames.elclasico.ImageManipulation

import com.dugonggames.elclasico.Classifiers.ClassCounts
import com.dugonggames.elclasico.Classifiers.FeatureVector
import com.dugonggames.elclasico.Classifiers.LabeledSample
import com.dugonggames.elclasico.Classifiers.Tree.Companion.chooseBestSplit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class TreeTest{
    @Test
    fun testChooseBestSplit1(){
        val image = Array(10){i -> LabeledSample(FeatureVector(floatArrayOf(i.toFloat())), i)}
        val all = ClassCounts(10)
        for (i in 0 until 10) all.increment(i)
        val split = chooseBestSplit(image, 0, 10, all, 0)
        assertEquals(split.bestPurity, 1.111111f, 0.001f)
        assertEquals(split.bestThreshold, 0.5f)
    }

    @Test
    fun testChooseBestSplit2(){
        val floats = floatArrayOf(10f, 15f, 19f, 27f, 37f, 72f, 82f, 83f, 94f, 97f)
        val labels = intArrayOf(0, 1, 1, 0, 1, 0, 1, 0, 0, 0)
        val image = Array(10){i -> LabeledSample(FeatureVector(floatArrayOf(floats[i])), labels[i])}
        val all = ClassCounts(2)
        for (i in 0 until 6) all.increment(0)
        for (i in 0 until 4) all.increment(1)
        val split = chooseBestSplit(image, 0, 10, all, 0)
        assertEquals(split.bestPurity, 1.5102041f, 0.001f)
        assertEquals(split.bestThreshold, 82.5f)
    }
}