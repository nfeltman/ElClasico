package com.dugonggames.elclasico

class DigitImage constructor(
        val digit: FloatArray,
        val label: Int = 0
) {

    companion object {
        fun fromFile(bytearr: ByteArray, offset: Int, labels: ByteArray, loffset: Int): DigitImage {
            val pixels = FloatArray(784) {i ->
                val f = bytearr[i + offset] / 256f
                if (f < 0) f + 1 else f
            }

            return DigitImage(pixels, labels[loffset].toInt())
        }
    }

    fun print() {
        for (i in 0..782) {
            if (i % 28 == 0) println()
            if (digit[i] > 0.5)
                print("*")
            else if (digit[i] > 0.25)
                print(".")
            else
                print(" ")
        }
    }

    fun numFeatures(): Int {
        return 784
    }

    fun compareTo(other: DigitImage, pixel: Int): Int {
        return if (other.digit[pixel] > digit[pixel])
            -1
        else if (other.digit[pixel] < digit[pixel])
            1
        else
            0
    }
}
