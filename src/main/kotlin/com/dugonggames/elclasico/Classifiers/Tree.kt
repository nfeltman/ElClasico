package com.dugonggames.elclasico.Classifiers

import com.dugonggames.elclasico.*
import java.util.Arrays
import java.util.Comparator

data class Tree constructor(val tree: Node<Int>) {

    fun classify(image: FeatureVector): Int {
        return tree.predict(image)
    }


}
