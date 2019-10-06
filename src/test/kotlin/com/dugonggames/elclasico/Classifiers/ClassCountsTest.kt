package com.dugonggames.elclasico.Classifiers

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ClassCountsTest{
    @Test
    fun testAllSame(){
        val sameCounts = ClassCounts(10)
        sameCounts.increment(2)
        sameCounts.increment(2)
        assertEquals(2, sameCounts.allSame())
        val diffCounts = ClassCounts(10)
        diffCounts.increment(2)
        diffCounts.increment(3)
        assertEquals(null, diffCounts.allSame())
    }
}