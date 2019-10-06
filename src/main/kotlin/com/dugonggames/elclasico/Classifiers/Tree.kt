package com.dugonggames.elclasico.Classifiers

import com.dugonggames.elclasico.*
import java.util.Arrays
import java.util.Comparator

class Tree private constructor(val tree: Node<Int>) {

    fun classify(image: DigitImage): Int {
        return tree.predict(image)
    }

    companion object {

        data class Split(val bestPurity: Float, val bestThreshold: Float)

        fun buildTree(images: Array<LabeledSample>): Tree =
                Tree(buildNode(images, 3, 0, images.size, 10))


        fun buildNode(images: Array<LabeledSample>, maxDepth: Int, low: Int, high: Int, numClasses: Int): Node<Int> {
            val countAll = countRange(images, low,  high, numClasses)
            if (maxDepth <= 1) return Leaf(countAll.highestValue())
            val index = countAll.allSame()
            if (index != null) return Leaf(index)

            var bestPurity = 0f
            var bestIndex = -1
            var bestThreshold = -1f

            val all = countRange(images, low, high, numClasses)

            for (i in 0 until images[low].numFeatures) {
                val best = chooseBestSplit(images, low, high, all, i)
                if (best.bestPurity > bestPurity){
                    bestPurity = best.bestPurity
                    bestIndex = i
                    bestThreshold = best.bestThreshold
                }
                if (i % 100 == 0 && i > 0) println(i)
            }
            val pi = images.partition(low, high) { i -> i.fv[bestIndex] < bestThreshold}
            println("pi: $pi")
            return Branch(
                    buildNode(images, maxDepth - 1, low, pi, numClasses),
                    buildNode(images, maxDepth - 1, pi, high, numClasses),
                    bestIndex, bestThreshold)
        }

        fun chooseBestSplit(images: Array<LabeledSample>, low: Int, high: Int, all: ClassCounts, i: Int):Split{
            Arrays.sort(images, low, high, Comparator.comparing<LabeledSample, Float> { d -> d.fv[i] })
            val left = ClassCounts(all.counts.size)
            val right = all.clone()
            var bestPurity = 0f
            var bestThreshold = 0f
            for (j in low+1 until high) {
                if (j > low) {
                    right.decrement(images[j-1].label)
                    left.increment(images[j-1].label)
                }
                if (left.gimiPurity() + right.gimiPurity() > bestPurity) {
                    bestPurity = left.gimiPurity() + right.gimiPurity()
                    bestThreshold = (images[j].fv[i] + (images[j-1].fv[i]))/2
                    //leftBest = left.highestValue();
                    //rightBest = right.highestValue();
                }
            }
            return Split(bestPurity, bestThreshold)
        }

        fun countRange(images: Array<LabeledSample>, low: Int, high: Int, numClasses: Int): ClassCounts {
            val all = ClassCounts(numClasses)
            for (j in low until high) {
                all.increment(images[j].label)
            }
            return all
        }
    }
}
