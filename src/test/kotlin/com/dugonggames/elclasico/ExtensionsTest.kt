package com.dugonggames.elclasico

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ExtensionsTest {
    @Test
    fun testPartition() {
        val a = arrayOf("New Mexico", "Montana", "New York", "Kentucky", "Hawaii", "Minnesota", "Georgia", "Utah",
                "Wisconsin", "Illinois", "California", "Michigan", "Arizona", "Virginia", "Florida", "Nevada", "Ohio",
                "Pennsylvania", "North Dakota", "Oregon", "Arkansas", "Wyoming", "Tennessee", "Mississippi",
                "Rhode Island", "Oklahoma", "Alabama", "Indiana", "Louisiana", "Connecticut", "Texas", "South Carolina",
                "Alaska", "Washington", "South Dakota", "Maine", "North Carolina", "Maryland", "West Virginia", "Iowa",
                "Colorado", "New Jersey", "Idaho", "Vermont", "Nebraska", "Delaware", "Kansas", "New Hampshire",
                "Missouri", "Massachusetts")
        val a2 = a.clone()

        val p1 = a.partition(0, 50) { s -> s.startsWith("M") }

        assertEquals(8, p1)
        assertEquals(setOf("Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
                "Missouri", "Montana"), a.take(p1).toSet())
        assertEquals(a.toList().sorted(), a2.toList().sorted())
        //expected: <[Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware, Florida, Georgia, Hawaii, Idaho, Illinois, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana, Maine, Maryland, Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana, Nebraska, Nevada, New Hampshire, New Jersey, New Mexico, New York, North Carolina, North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina, South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia, Wyoming]>
        // but was: <[Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware, Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana, Maine, Maryland, Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana, Nebraska, Nevada, New Hampshire, New Jersey, New Mexico, New York, North Carolina, North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina, South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia, Wisconsin, Wyoming]>
    }

    @Test
    fun testPartition2() {
        val a = arrayOf(4, 8)
        val a2 = a.clone()

        val p1 = a.partition(0, 2) { s -> s>6 }

        assertEquals(1, p1)
        assertEquals(setOf(8), a.take(p1).toSet())
        assertEquals(a.toList().sorted(), a2.toList().sorted())
    }
}