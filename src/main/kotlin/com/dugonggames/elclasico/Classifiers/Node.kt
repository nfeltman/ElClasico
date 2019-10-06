package com.dugonggames.elclasico.Classifiers

import com.dugonggames.elclasico.DigitImage

interface Node<T> {
    fun predict(im: DigitImage): T
}

data class Leaf<T>(internal var item: T) : Node<T> {

    override fun predict(im: DigitImage): T {
        return item
    }
}

data class Branch<T>(var l: Node<T>, var r: Node<T>, var xIndex: Int, var xThreshold: Float) : Node<T> {

    override fun predict(im: DigitImage): T {
        return if (im.digit[xIndex] < xThreshold)
            l.predict(im)
        else
            r.predict(im)
    }
}
