package com.dugonggames.elclasico

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
        return if (im.getDigit()[xIndex] < xThreshold)
            l.predict(im)
        else
            r.predict(im)
    }
}
