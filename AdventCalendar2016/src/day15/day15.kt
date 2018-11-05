package day15

import java.io.File

data class Disc(val id: Int,
                val positions: Int,
                var currentPosition: Int)

fun main(args: Array<String>) {
    val discs = mutableListOf<Disc>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day15/day15src.txt").forEachLine { line ->
        val l = line.replace("#", "").replace(".", "").split(" ")
        discs.add(Disc(l[1].toInt(), l[3].toInt(), l[11].toInt()))
    }

    val startingTime1 = findIdeadStartTime(discs)
    discs.add(Disc(discs.size+1, 11, 0))
    val startingTime2 = findIdeadStartTime(discs)

    println("part1: $startingTime1")
    println("part1: $startingTime2")
}

fun findIdeadStartTime(discs: MutableList<Disc>): Int {
    var foundPart = false
    var startingTime = 1
    while (foundPart.not()) {
        foundPart = true
        discs.forEachIndexed { i, disc ->
            if ((disc.currentPosition + startingTime + i + 1) % disc.positions != 0) {
                foundPart = false
            }
        }
        if(foundPart) {
            return startingTime
        }
        startingTime++
    }

    return  startingTime
}