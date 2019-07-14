package com.dugonggames.elclasico

interface Classifier {
    fun classify(image: DigitImage): Byte
}
