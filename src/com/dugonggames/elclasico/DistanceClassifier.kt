package com.dugonggames.elclasico

class DistanceClassifier(images: Array<DigitImage>, labels: IntArray) {
    internal var avgs: Array<DigitImage>

    init {
        avgs = Array(10) { DigitImage(FloatArray(784), 0) }
        val counts = IntArray(10)

        for (i in 0..9999) {
            avgs[labels[i]] = avgs[labels[i]].add(images[i])
            counts[labels[i]]++
        }

        for (i in 0..9) avgs[i] = avgs[i].divide(counts[i])
    }

    fun eClassify(image: DigitImage): Int {
        var smallestDist = java.lang.Double.POSITIVE_INFINITY
        var smallestDistIndex = -1
        for (i in 0..9) {
            if (image.eDistance(avgs[i]) < smallestDist) {
                smallestDist = image.eDistance(avgs[i])
                smallestDistIndex = i
            }
        }
        return smallestDistIndex
    }

    fun tClassify(image: DigitImage): Int {
        var smallestDist = java.lang.Double.POSITIVE_INFINITY
        var smallestDistIndex = -1
        for (i in 0..9) {
            if (image.tDistance(avgs[i]) < smallestDist) {
                smallestDist = image.tDistance(avgs[i])
                smallestDistIndex = i
            }
        }
        return smallestDistIndex
    }
}
