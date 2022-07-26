package org.techtown.medexhealing.Graph

data class Graph(
    var code: Int,
    var msg: String,
    var entem: String,
    var enhum: String,
    var enco2: String,
    var snore: ArrayList<Int>,
    var sleep: ArrayList<Int>
)
