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
            val pi = partition(images, bestIndex, bestThreshold, low, high)
            println("pi: $pi")
            return Branch(buildNode(images, maxDepth - 1, low, pi), buildNode(images, maxDepth - 1, pi, high), bestIndex, bestThreshold)
        }

        private fun partition(images: Array<DigitImage>, pixel: Int, threshold: Float, low: Int, high: Int): Int {
            println(pixel.toString() + ":" + threshold)
            var i = low
            for (j in low until high) {
                if (images[j].digit[pixel] < threshold) {
                    val temp = images[i]
                    images[i] = images[j]
                    images[j] = temp
                    i++
                }
            }
            val temp = images[i + 1]
            images[i] = images[high - 1]
            images[high - 1] = temp
            return i
        }
    }
}
