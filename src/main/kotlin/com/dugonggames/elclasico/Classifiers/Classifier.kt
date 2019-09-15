package com.dugonggames.elclasico.Classifiers

import com.dugonggames.elclasico.DigitImage

interface Classifier {
    fun classify(image: DigitImage): Byte
}
