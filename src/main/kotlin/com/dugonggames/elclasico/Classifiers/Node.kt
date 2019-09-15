package com.dugonggames.elclasico.Classifiers

import com.dugonggames.elclasico.DigitImage

interface Node<T> {
    fun predict(im: DigitImage): T
}

class Leaf<T>(internal var item: T) : Node<T> {

    override fun predict(im: DigitImage): T {
        return item
    }
}

class Branch<T>(internal var l: Node<T>, internal var r: Node<T>, internal var xIndex: Int, internal var xThreshold: Float) : Node<T> {

    override fun predict(im: DigitImage): T {
        return if (im.digit[xIndex] < xThreshold)
            l.predict(im)
        else
            r.predict(im)
    }
}
