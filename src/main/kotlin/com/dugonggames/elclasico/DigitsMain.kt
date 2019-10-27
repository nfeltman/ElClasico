package com.dugonggames.elclasico

import com.dugonggames.elclasico.Classifiers.BuildTree.buildForest
import com.dugonggames.elclasico.Classifiers.BuildTree.buildTree
import com.dugonggames.elclasico.Classifiers.FeatureVector
import com.dugonggames.elclasico.Classifiers.LabeledSample
import com.dugonggames.elclasico.ImageManipulation.Pyramid
import com.dugonggames.elclasico.ImageManipulation.createGaussianPyramid
import com.dugonggames.elclasico.ImageManipulation.createLaplacianPyramid
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

        println("Loading images.")
        val trainingimages = Array(60000){ i ->
            val d=DigitImage.fromFile(trainingimagesbytes, 16 + 784 * i, traininglabelsbytes, 8 + i)
            LabeledSample(preprocessImage(d), d.label)
        }
        println("Done loading.")

        //trainingimages.forEach { println(it.fv[387]) }

        //val t = buildTree(images = trainingimages, numClasses = 10, maxDepth = 1)

        val f = buildForest(20, trainingimages, 10, 6, 28, 20)

        val testimages = Array(10000) {
            i -> DigitImage.fromFile(testimagesbytes, 16 + 784 * i, testlabelsbytes, 8 + i)
        }

        val correct = testimages.count { it.label == f.classify(preprocessImage(it)) }
        println(correct)
    }

    fun preprocessImage(digitImage: DigitImage): FeatureVector {
        return FeatureVector(digitImage.digit)
        //return FeatureVector(createGaussianPyramid(digitImage.digit, 28, 28).data)
        //return FeatureVector(createLaplacianPyramid(digitImage.digit, 28, 28).data)
    }
}
