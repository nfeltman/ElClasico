package com.dugonggames.elclasico

data class PyramidLevel(
        val start: Int,
        val width: Int,
        val height: Int
)

data class Pyramid(
        val data: IntArray,
        val levels: List<PyramidLevel>
) {
    val numLevels: Int = levels.size

    // overrides []
    operator fun get(level: Int, x: Int, y: Int): Int {
        val l = levels[level]
        return data[l.start+(x*l.height)+y]
    }
}

fun createGaussianPyramid(source: IntArray, w:Int, h:Int): Pyramid {
    var totalSize = 0
    var tempW = w; var tempH = h;
    while (tempW != 1 || tempH != 1){
        totalSize += tempW * tempH
        tempW = half(tempW);
        tempH = half(tempH);
    }
    totalSize++ //To account for the final addition of 1*1
    println(totalSize)
    val out = IntArray(totalSize);
    for (i in 0 until source.size) out[i] = source[i]
    var lastlevel = PyramidLevel(0, w, h)
    var levellist = mutableListOf<PyramidLevel>(lastlevel)

    while (lastlevel.width > 1 || lastlevel.height > 1) {
        val downres = downsample(out, lastlevel.start, lastlevel.width, lastlevel.height, out,
                lastlevel.start + (lastlevel.width * lastlevel.height))
        lastlevel = PyramidLevel(lastlevel.start + (lastlevel.width * lastlevel.height), downres.outputWidth,
                downres.outputHeight)
        levellist.add(lastlevel)
    }

    return Pyramid(out, levellist)
}

fun createLaplacianPyramid(gaussianPyramid: Pyramid): Pyramid {
    TODO()
}

fun createLaplacianPyramid(source: IntArray, w:Int, h:Int): Pyramid {
    return createLaplacianPyramid(createGaussianPyramid(source, w, h))
}

fun testPyramid():Boolean{
    var image = IntArray(31*17,{i -> i})
    var pyramid = createGaussianPyramid(image, 31, 17)
    println(pyramid.data.joinToString())
    println(pyramid.levels)
    return false
}