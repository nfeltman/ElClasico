package com.dugonggames.elclasico


fun <T> Array<T>.partition(low: Int, high: Int, p: (T) -> Boolean): Int {
    var i = low
    for (j in low until high) {
        if (p(this[j])) {
            val temp = this[i]
            this[i] = this[j]
            this[j] = temp
            i++
        }
    }
    val temp = this[i + 1]
    this[i] = this[high - 1]
    this[high - 1] = temp
    return i
}


fun testPartition():Boolean {
    val a = arrayOf("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware",
            "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
            "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
            "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
            "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
            "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
            "Wisconsin", "Wyoming")
    val a2 = a.clone()

    val p1 = a.partition(0, 50) { s -> s.startsWith("M") }

    return p1 == 8 && a.take(p1).toSet() == setOf("Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
            "Missouri", "Montana")
}
