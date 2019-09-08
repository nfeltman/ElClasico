package com.dugonggames.elclasico.Classifiers

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

}

data class LabeledSample(val fv:FeatureVector, val label:Int)