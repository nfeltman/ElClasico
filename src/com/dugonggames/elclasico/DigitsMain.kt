package com.dugonggames.elclasico

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object DigitsMain {

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val trainingimagesbytes = Files.readAllBytes(Paths.get("C:\\Users\\alexf\\Downloads\\train-images-idx3-ubyte\\train-images.idx3-ubyte"))
        val traininglabelsbytes = Files.readAllBytes(Paths.get("C:\\Users\\alexf\\Downloads\\train-labels-idx1-ubyte\\train-labels.idx1-ubyte"))
        val testimagesbytes = Files.readAllBytes(Paths.get("C:\\Users\\alexf\\Downloads\\t10k-images-idx3-ubyte\\t10k-images.idx3-ubyte"))
        val testlabelsbytes = Files.readAllBytes(Paths.get("C:\\Users\\alexf\\Downloads\\t10k-labels-idx1-ubyte\\t10k-labels.idx1-ubyte"))

        val trainingimages = arrayOfNulls<DigitImage>(60000)
        for (i in 0..59999) trainingimages[i] = DigitImage(trainingimagesbytes, 16 + 784 * i, traininglabelsbytes, 8 + i)

        val t = Tree(trainingimages)

        val testimages = arrayOfNulls<DigitImage>(60000)
        for (i in 0..9999) testimages[i] = DigitImage(testimagesbytes, 16 + 784 * i, testlabelsbytes, 8 + i)

        var correct = 0
        for (i in 0..9999) {
            if (testimages[i]!!.getLabel() == t.classify(testimages[i])) correct++
        }
        println(correct)

        val num = 543
        testimages[num]!!.print()
        println(t.classify(testimages[num]))
        println(testimages[num]!!.getLabel())
    }
}
