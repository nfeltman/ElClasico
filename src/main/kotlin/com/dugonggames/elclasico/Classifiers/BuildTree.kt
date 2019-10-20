package com.dugonggames.elclasico.Classifiers

import com.dugonggames.elclasico.partition
import java.util.*

object BuildTree{

    data class Split(val purity: Float, val index: Int, val threshold: Float)

    fun buildTree(images: Array<LabeledSample>, numClasses: Int, maxDepth: Int, numFeatures: Int=images[0].numFeatures): Tree =
            Tree(buildNode(images, maxDepth, 0, images.size, numClasses))

    fun buildNode(images: Array<LabeledSample>, maxDepth: Int, low: Int, high: Int, numClasses: Int, numFeatures: Int): Node<Int> {
        val countAll = countRange(images, low, high, numClasses)
        if (maxDepth <= 1) return Leaf(countAll.highestValue())
        val index = countAll.allSame()
        if (index != null) return Leaf(index)

        var bestSplit: Split? = null

        val all = countRange(images, low, high, numClasses)

        for (i in 0 until images[low].numFeatures) {
            val featureSplit = chooseBestSplit(images, low, high, all, i)
            if (bestSplit == null || featureSplit != null && featureSplit.purity > bestSplit.purity){
                bestSplit = featureSplit
            }
            if (i % 100 == 0 && i > 0) println(i)
        }

        if(bestSplit == null) return Leaf(countAll.highestValue())

        val pi = images.partition(low, high) { i -> i.fv[bestSplit.index] < bestSplit.threshold}
        println("Selected threshold of ${bestSplit.threshold} on feature ${bestSplit.index}, yielding partition value: $pi")
        return Branch(
                buildNode(images, maxDepth - 1, low, pi, numClasses),
                buildNode(images, maxDepth - 1, pi, high, numClasses),
                bestSplit.index, bestSplit.threshold)
    }

    fun chooseBestSplit(images: Array<LabeledSample>, low: Int, high: Int, all: ClassCounts, i: Int) : Split? {
        Arrays.sort(images, low, high, Comparator.comparing<LabeledSample, Float> { d -> d.fv[i] })
        val left = ClassCounts(all.counts.size)
        val right = all.clone()
        var bestSplit: Split? = null
        for (j in low+1 until high) {
            if (j > low) {
                right.decrement(images[j-1].label)
                left.increment(images[j-1].label)
            }
            val leftOfSplit = images[j-1].fv[i]
            val rightOfSplit = images[j].fv[i]
            val splitPurity = (left.giniPurity()*left.sum + right.giniPurity()*right.sum) / (left.sum + right.sum)
            if (leftOfSplit != rightOfSplit && (bestSplit == null || splitPurity > bestSplit.purity)) {
                bestSplit = Split(splitPurity, i, (leftOfSplit + rightOfSplit) * 0.5f)
            }
        }
        // if(bestSplit != null) println("Best split for feature $i is ${bestSplit.threshold} yielding purity of ${bestSplit.purity}")
        return bestSplit
    }

    fun countRange(images: Array<LabeledSample>, low: Int, high: Int, numClasses: Int): ClassCounts {
        val all = ClassCounts(numClasses)
        for (j in low until high) {
            all.increment(images[j].label)
        }
        return all
    }

    fun randFeature(max: Int, n: Int): List<Int>{
        var features = (0..max).toMutableList()
        var result = List<Int>(n){i ->
            val rand = (Math.random()*features.size).toInt()
            features.removeAt(rand)
        }
        return result
    }
}