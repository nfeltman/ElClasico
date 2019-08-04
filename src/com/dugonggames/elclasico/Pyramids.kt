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
        TODO()
    }
}

fun createGaussianPyramid(source: IntArray): Pyramid {
    TODO()
}

fun createLaplacianPyramid(gaussianPyramid: Pyramid): Pyramid {
    TODO()
}

fun createLaplacianPyramid(source: IntArray): Pyramid {
    TODO()
}