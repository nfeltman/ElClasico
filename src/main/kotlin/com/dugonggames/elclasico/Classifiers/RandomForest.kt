package com.dugonggames.elclasico.Classifiers

class RandomForest(val trees: List<Tree>) {
    fun classify(image: FeatureVector): Int {
        var cc = ClassCounts(10)
        for (tree in trees){
            cc.increment(tree.classify(image))
        }
        return cc.highestValue()
    }


}