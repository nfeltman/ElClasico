package com.dugonggames.elclasico.Classifiers

import com.dugonggames.elclasico.DigitImage

class DistanceClassifier(images: Array<FeatureVector>, labels: IntArray) {
    internal var avgs: Array<LabeledSample>

    init {
        avgs = Array(10) { LabeledSample(FeatureVector(FloatArray(784)), 0) }
        val counts = IntArray(10)

        var temps = Array(10){FeatureVector(FloatArray(784))}

        for (i in 0..9999) {
            temps[labels[i]] += images[i]
            counts[labels[i]]++
        }

        for (i in 0..9) avgs[i] = LabeledSample(temps[i]/(counts[i]), i)
    }

    fun eClassify(image: FeatureVector): Int {
        var smallestDist = java.lang.Double.POSITIVE_INFINITY
        var smallestDistIndex = -1
        for (i in 0..9) {
            if (image.eDistance(avgs[i].fv) < smallestDist) {
                smallestDist = image.eDistance(avgs[i].fv)
                smallestDistIndex = i
            }
        }
        return smallestDistIndex
    }

    fun tClassify(image: FeatureVector): Int {
        var smallestDist = java.lang.Double.POSITIVE_INFINITY
        var smallestDistIndex = -1
        for (i in 0..9) {
            if (image.tDistance(avgs[i].fv) < smallestDist) {
                smallestDist = image.tDistance(avgs[i].fv)
                smallestDistIndex = i
            }
        }
        return smallestDistIndex
    }
}
