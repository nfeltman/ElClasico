package com.dugonggames.elclasico

import com.dugonggames.elclasico.Classifiers.FeatureVector
import com.dugonggames.elclasico.Classifiers.LabeledSample
import com.dugonggames.elclasico.Classifiers.Tree
import com.dugonggames.elclasico.ImageManipulation.createLaplacianPyramid
import java.nio.file.Files
import java.nio.file.Paths

object DigitsMain {

    @JvmStatic
    fun main(args: Array<String>) {
        val root = System.getProperty("user.home")
        val rootDataDirectory = Paths.get(root).resolve("Downloads").resolve("dataset")
        val trainingimagesbytes = rootDataDirectory.resolve("train-images.idx3-ubyte").toFile().readBytes()
        val traininglabelsbytes = rootDataDirectory.resolve("train-labels.idx1-ubyte").toFile().readBytes()
        val testimagesbytes = rootDataDirectory.resolve("t10k-images.idx3-ubyte").toFile().readBytes()
        val testlabelsbytes = rootDataDirectory.resolve("t10k-labels.idx1-ubyte").toFile().readBytes()

        val trainingimages = Array(60000){
            i -> val d=DigitImage.fromFile(trainingimagesbytes, 16 + 784 * i, traininglabelsbytes, 8 + i)
                 LabeledSample(FeatureVector(d.digit), d.label)
        }

        //trainingimages.forEach { println(it.fv[387]) }

        val t = Tree.buildTree(trainingimages, 10)

        //println("built: $t")

        val testimages = Array(10000) {
            i -> DigitImage.fromFile(testimagesbytes, 16 + 784 * i, testlabelsbytes, 8 + i)
        }

        var correct = 0
        for (i in 0..9999) {
            if (testimages[i].label == t.classify(testimages[i])) correct++
        }
        println(correct)
    }
}
