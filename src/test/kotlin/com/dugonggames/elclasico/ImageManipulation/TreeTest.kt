package com.dugonggames.elclasico.ImageManipulation

import com.dugonggames.elclasico.Classifiers.*
import com.dugonggames.elclasico.Classifiers.Tree.Companion.buildNode
import com.dugonggames.elclasico.Classifiers.Tree.Companion.chooseBestSplit
import com.dugonggames.elclasico.Classifiers.Tree.Companion.countRange
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class TreeTest{
    @Test
    fun testChooseBestSplit1(){
        val images = Array(10){i -> LabeledSample(FeatureVector(floatArrayOf(i.toFloat())), i)}
        val all = ClassCounts(10)
        for (i in 0 until 10) all.increment(i)
        val split = chooseBestSplit(images, 0, 10, all, 0)!!
        assertEquals(0.2f, split.purity, 0.001f)
        assertEquals(2.5f, split.threshold)
    }

    @Test
    fun testChooseBestSplit2(){
        val floats = floatArrayOf(10f, 15f, 19f, 27f, 37f, 72f, 82f, 83f, 94f, 97f)
        val labels = intArrayOf(0, 1, 1, 0, 1, 0, 1, 0, 0, 0)
        val images = Array(10){i -> LabeledSample(FeatureVector(floatArrayOf(floats[i])), labels[i])}
        val all = countRange(images, 0, 10, 2)
        val split = chooseBestSplit(images, 0, 10, all, 0)!!
        assertEquals(0.6571428f, split.purity, 0.001f)
        assertEquals(82.5f, split.threshold)
    }

    @Test
    fun `testChooseBestSplit with uneven range`(){
        val floats = floatArrayOf(0f, 0f, 0f, 10f, 15f, 19f, 27f, 37f, 72f, 82f, 83f, 94f, 97f, 0f, 0f, 0f)
        val labels = intArrayOf(0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0)
        val images = Array(16){i -> LabeledSample(FeatureVector(floatArrayOf(floats[i])), labels[i])}
        val all = countRange(images, 3, 13, 2)
        val split = chooseBestSplit(images, 3, 13, all, 0)!!
        assertEquals(0.6571428f, split.purity, 0.001f)
        assertEquals(82.5f, split.threshold)
    }

    @Test
    fun `testChooseBestSplit with uneven range unsorted`(){
        val floats = floatArrayOf(19f, 83f, 97f, 72f, 82f, 15f, 37f, 27f, 10f, 94f)
        val labels = intArrayOf(1, 0, 0, 0, 1, 1, 1, 0, 0, 0)
        val images = Array(10){i -> LabeledSample(FeatureVector(floatArrayOf(floats[i])), labels[i])}
        val all = countRange(images, 0, 10, 2)
        val split = chooseBestSplit(images, 0, 10, all, 0)!!
        assertEquals(0.6571428f, split.purity, 0.001f)
        assertEquals(82.5f, split.threshold)
    }

    @Test
    fun `testChooseBestSplit with non-unique features`(){
        val floats = floatArrayOf(0f, 0f, 1f, 1f)
        val labels = intArrayOf(0, 1, 1, 1)
        val images = Array(4){i -> LabeledSample(FeatureVector(floatArrayOf(floats[i])), labels[i])}
        val all = countRange(images, 0, 4, 2)
        val split = chooseBestSplit(images, 0, 4, all, 0)!!
        assertEquals(0.75f, split.purity, 0.001f)
        assertEquals(0.5f, split.threshold)
    }

    @Test
    fun `test buildNode`(){
        val floats = floatArrayOf(10f, 15f, 19f, 27f, 37f, 72f, 82f, 83f, 94f, 97f)
        val labels = intArrayOf(0, 1, 1, 0, 1, 0, 1, 0, 0, 0)
        val images = Array(10){i -> LabeledSample(FeatureVector(floatArrayOf(floats[i])), labels[i])}
        val all = ClassCounts(10)
        for (i in 0 until 10) all.increment(i)
        val shallowTree = buildNode(images, 2, 0, 10, 2)

        val correctTree = Branch(Leaf(1), Leaf(0), 0, 82.5f)
        assertEquals(shallowTree, correctTree)
    }

    @Test
    fun `test buildNode 3 levels`(){
        val floats = floatArrayOf(10f, 15f, 19f, 27f, 37f, 72f, 82f, 83f, 94f, 97f)
        val labels = intArrayOf(0, 1, 1, 0, 1, 0, 1, 0, 0, 0)
        val images = Array(10){i -> LabeledSample(FeatureVector(floatArrayOf(floats[i])), labels[i])}
        val tree = buildNode(images, 3, 0, 10, 2)

        val correctTree = Branch(Leaf(1), Leaf(0), 0, 82.5f)
        val correctLeft = Branch(Leaf(0), Leaf(1), 0, 12.5f)
        val correctRight = Leaf(0)
        assertEquals(correctLeft, (tree as Branch).l)
        assertEquals(correctRight, (tree as Branch).r)
    }
}