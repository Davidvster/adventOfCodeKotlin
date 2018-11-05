package day6

import java.io.File

fun main(args: Array<String>) {

    var message1 = ""
    var message2 = ""
    val maps = mutableListOf<MutableMap<Char, Int>>(
    )
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day6/day6src.txt").forEachLine { line ->
        line.forEachIndexed { index, c ->
            if (maps.size <= index) {
                maps.add(mutableMapOf(Pair(c, 1)))
            } else {
                maps[index][c] = (maps[index][c]?:0) + 1
            }
        }
    }
    //part1
    maps.forEach {
        val sorted = it.toList().sortedByDescending { (_, value) -> value }
        message1 += sorted.first().first
    }
    //part2
    maps.forEach {
        val sorted = it.toList().sortedBy { (_, value) -> value }
        message2 += sorted.first().first
    }
    println("part1: $message1")
    println("part2: $message2")
}
