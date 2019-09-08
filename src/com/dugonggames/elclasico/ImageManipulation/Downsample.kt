package com.dugonggames.elclasico.ImageManipulation

import java.lang.Integer.min

fun half(i: Int): Int {
    return if (i % 2 == 0)
        i / 2
    else
        i / 2 + 1
}

data class DownsampleResults(
    val outputWidth: Int,
    val outputHeight: Int,
    val endIndex: Int
)

fun downsample(source: IntArray, sourceOffset: Int, w: Int, h: Int, target: IntArray, targetOffset: Int) : DownsampleResults {
    val outputW = half(w)
    val outputH = half(h)
    for (i in 0 until outputW) {
        for (j in 0 until outputH) {
            val r2 = min(((j*2) + 1), h-1)
            val c2 = min((i * 2 + 1), w-1)
            val x1 = ((i * 2) * h + (j*2))
            val x2 = (c2 * h + (j*2))
            val y1 = ((i * 2) * h + r2)
            val y2 = (c2 * h + r2)
            val sum = source[sourceOffset+x1] + source[sourceOffset+x2] + source[sourceOffset+y1] + source[sourceOffset+y2]
            target[targetOffset+(i * outputH + j)] = sum / 4
            println()
        }
    }
    return DownsampleResults(outputW, outputH, targetOffset + (outputW * outputH))
}

fun testDownsample(){
    val source = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25)
    //val source = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
    // (3.5, 5.5, 11.5, 13.5, 0, 0, 0, 0, 0)
    val target = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    val results = downsample(source, 0, 5, 5, target, 0)
    println(target.joinToString())
}

fun testDownsample2(){
    val source = intArrayOf(5)
    val target = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    val results = downsample(source, 0, 1, 1, target, 0)
    println(target.joinToString())
}
