package com.dugonggames.elclasico.Classifiers

import com.dugonggames.elclasico.DigitImage
import java.util.*

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
        return FeatureVector(FloatArray(784) { i->  fa[i] + other.fa[i] })
    }

    fun subtract(other: FeatureVector): FeatureVector {
        return FeatureVector(FloatArray(784){ i -> fa[i] - other.fa[i] })
    }

    fun divide(denom: Int): FeatureVector {
        val result = FloatArray(784)
        for (i in 0..783) result[i] = fa[i] / denom
        return FeatureVector(result)
    }

    // Euclidian distance
    fun eDistance(other: FeatureVector): Double {
        var sum = 0.0
        for (i in 0..783) sum += ((fa[i] - other.fa[i]) * (fa[i] - other.fa[i])).toDouble()
        return Math.sqrt(sum)
    }

    // Taxicab distance
    fun tDistance(other: FeatureVector): Double {
        var sum = 0.0
        for (i in 0..783) sum += Math.abs(fa[i] - other.fa[i]).toDouble()
        return sum
    }

    operator fun plus(temp: FeatureVector): FeatureVector {
        return this.add(temp)
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