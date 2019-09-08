package com.dugonggames.elclasico.Classifiers

class ClassCounts(
        val counts: IntArray,
        var sum: Int
) {

    constructor(numClasses: Int) : this(IntArray(numClasses), 0)

    fun clone() : ClassCounts = ClassCounts(counts.clone(), sum)

    fun propMatching(i: Int): Float {
        return (counts[i] + 0f) / sum
    }

    fun gimiPurity(): Float {
        var sum = 0f
        for (i in counts.indices) {
            sum += propMatching(i) * propMatching(i)
        }
        return sum
    }

    fun randomSelect(): Int {
        var cumeProb = 0f
        val random = Math.random()
        for (i in counts.indices) {
            cumeProb += propMatching(i)
            if (random < cumeProb) return i
        }
        return -1
    }

    fun highestValue(): Int {
        var highest = -1
        var highestIndex = -1
        for (i in counts.indices) {
            if (counts[i] > highest) {
                highest = counts[i]
                highestIndex = i
            }
        }
        return highestIndex
    }

    fun increment(i: Int) {
        counts[i]++
        sum++
    }

    fun increment(i: Int, amount: Int) {
        counts[i] += amount
        sum += amount
    }

    fun decrement(i: Int) {
        counts[i]--
        sum--
    }

    fun decrement(i: Int, amount: Int) {
        counts[i] -= amount
        sum -= amount
    }
}
