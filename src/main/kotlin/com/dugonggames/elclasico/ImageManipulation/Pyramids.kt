package com.dugonggames.elclasico.ImageManipulation

data class PyramidLevel(
        val start: Int,
        val width: Int,
        val height: Int
)

data class Pyramid(
        val data: FloatArray,
        val levels: List<PyramidLevel>
) {
    val numLevels: Int = levels.size

    // overrides []
    operator fun get(level: Int, x: Int, y: Int): Float {
        val l = levels[level]
        return data[l.start+(x*l.height)+y]
    }
}

fun createGaussianPyramid(source: FloatArray, w:Int, h:Int): Pyramid {
    var totalSize = 0
    var tempW = w; var tempH = h;
    while (tempW != 1 || tempH != 1){
        totalSize += tempW * tempH
        tempW = half(tempW);
        tempH = half(tempH);
    }
    totalSize++ //To account for the final addition of 1*1
    val out = FloatArray(totalSize);
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
    var data = FloatArray(gaussianPyramid.data.size)
    gaussianPyramid.levels.zipWithNext { a, b ->  for (i in 0 until a.width){
        for (j in 0 until a.height){
            //println("$i, $j, $a, $b, ${b.start+(i/2*b.height)+j/2}")
            var levelUp=gaussianPyramid.data[b.start+(i/2*b.height)+j/2]
            data[a.start+(i*a.height)+j]=(gaussianPyramid.data[a.start+(i*a.height)+j]-levelUp)
        }
    }}
    data[data.size-1] = gaussianPyramid.data[data.size-1]
    return Pyramid(data, gaussianPyramid.levels)
}

fun createLaplacianPyramid(source: FloatArray, w:Int, h:Int): Pyramid {
    return createLaplacianPyramid(createGaussianPyramid(source, w, h))
}