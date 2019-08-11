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
    val out = IntArray(100);
    for (i in 0 until source.size) out[i] = source[i]
    var lastlevel = PyramidLevel(0, w, h)
    while (lastlevel.width > 1 || lastlevel.height > 1) {
        val downres = downsample(source, lastlevel.start, lastlevel.width, lastlevel.height, out,
                lastlevel.start + (lastlevel.width * lastlevel.height))
        lastlevel = PyramidLevel(lastlevel.start + (lastlevel.width * lastlevel.height), downres.outputWidth,
                downres.outputHeight)
    }
}

fun createLaplacianPyramid(gaussianPyramid: Pyramid): Pyramid {
    TODO()
}

fun createLaplacianPyramid(source: IntArray): Pyramid {
    return createLaplacianPyramid(createGaussianPyramid(source))
}