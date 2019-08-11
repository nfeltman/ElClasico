package com.dugonggames.elclasico

import java.util.Arrays
import java.util.Comparator

class Tree private constructor(val tree: Node<Int>) {

    fun classify(image: DigitImage): Int {
        return tree.predict(image)
    }

    companion object {

        fun buildTree(images: Array<DigitImage>): Tree =
            Tree(buildNode(images, 3, 0, images.size))


        fun buildNode(images: Array<DigitImage>, maxDepth: Int, low: Int, high: Int): Node<Int> {
            val countAll = ClassCounts(10)
            for (j in low until high) {
                countAll.increment(images[j].label)
            }

            if (maxDepth <= 1) return Leaf(countAll.highestValue())
            var bestPurity = 0f
            var bestIndex = -1
            var bestThreshold = -1f


            val all = ClassCounts(10)
            for (j in low until high) {
                all.increment(images[j].label)
            }

            for (i in 0 until images[low].numFeatures()) {
                Arrays.sort(images, low, high, Comparator.comparing<DigitImage, Float> { d -> d.digit[i] })
                val left = ClassCounts(10)
                val right = all.clone()
                for (j in low until high) {
                    if (j > low) {
                        right.decrement(images[j - 1].label)
                        left.increment(images[j - 1].label)
                    }
                    if (left.gimiPurity() + right.gimiPurity() > bestPurity) {
                        bestPurity = left.gimiPurity() + right.gimiPurity()
                        bestIndex = i
                        bestThreshold = images[j].digit[i]
                        //leftBest = left.highestValue();
                        //rightBest = right.highestValue();
                    }
                }
                if (i % 100 == 0 && i > 0) println(i)
            }
            val pi = images.partition(low, high) { i -> i.digit[bestIndex] < bestThreshold}
            println("pi: $pi")
            return Branch(buildNode(images, maxDepth - 1, low, pi), buildNode(images, maxDepth - 1, pi, high), bestIndex, bestThreshold)
        }
    }
}
