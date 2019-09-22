package com.dugonggames.elclasico.Classifiers

import com.dugonggames.elclasico.DigitImage
import java.util.*

class DifferentLengthsException(s1: Int, s2: Int): RuntimeException("Tried to operate on vectors of differing lengths $s1 and $s2")

data class FeatureVector(val fa:FloatArray){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FeatureVector

        if (!Arrays.equals(fa, other.fa)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(fa)
    }


    fun add(other: FeatureVector): FeatureVector {
        if (fa.size != other.fa.size) throw DifferentLengthsException(fa.size, other.fa.size)
        return FeatureVector(FloatArray(fa.size) { i->  fa[i] + other.fa[i] })
    }

    fun subtract(other: FeatureVector): FeatureVector {
        if (fa.size != other.fa.size) throw DifferentLengthsException(fa.size, other.fa.size)
        return FeatureVector(FloatArray(fa.size){ i -> fa[i] - other.fa[i] })
    }

    fun divide(denom: Int): FeatureVector {
        val result = FloatArray(fa.size)
        for (i in 0..fa.size-1) result[i] = fa[i] / denom
        return FeatureVector(result)
    }

    // Euclidian distance
    fun eDistance(other: FeatureVector): Double {
        if (fa.size != other.fa.size) throw DifferentLengthsException(fa.size, other.fa.size)
        var sum = 0.0
        for (i in 0..fa.size-1) sum += ((fa[i] - other.fa[i]) * (fa[i] - other.fa[i])).toDouble()
        return Math.sqrt(sum)
    }

    // Taxicab distance
    fun tDistance(other: FeatureVector): Double {
        if (fa.size != other.fa.size) throw DifferentLengthsException(fa.size, other.fa.size)
        var sum = 0.0
        for (i in 0..fa.size-1) sum += Math.abs(fa[i] - other.fa[i]).toDouble()
        return sum
    }

    operator fun plus(temp: FeatureVector): FeatureVector {
        return this.add(temp)
    }

    operator fun minus(temp: FeatureVector): FeatureVector {
        return this.subtract(temp)
    }

    operator fun div(i: Int): FeatureVector {
        return this.divide(i)
    }

    operator fun get(i: Int): Float {
        return this.fa[i]
    }

}

data class LabeledSample(val fv:FeatureVector, val label:Int) {
    val numFeatures: Int
        get() {
            return fv.fa.size
        }
}