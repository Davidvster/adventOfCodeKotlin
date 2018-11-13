package day11

import java.io.File

fun main(args: Array<String>) {
    var r = 0
    var s = 0
    var distance2 = Int.MIN_VALUE
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2017/src/day11/day11src.txt").forEachLine { line ->
        val l = line.split(",")
        l.forEach {
            when (it) {
                "s" -> s++
                "sw" -> r++
                "se" -> {
                    r--
                    s++
                }
                "n" -> s--
                "nw" -> {
                    r++
                    s--
                }
                "ne" -> r--
            }
            val tmpDist = calculateDistanceToZero(r, s)
            if (tmpDist > distance2) distance2 = tmpDist
        }
    }
    val distance1 = calculateDistanceToZero(r, s)
    println("part1: $distance1")
    println("part1: $distance2")
}

fun calculateDistanceToZero(rIn: Int, sIn: Int): Int {
    var r = rIn
    var s = sIn
    var distanceToZero = 0
    if (r > 0 && s < 0) {
        distanceToZero += r
        s += r
        distanceToZero += Math.abs(s)
    } else if (r < 0 && s > 0) {
        distanceToZero += s
        r += s
        distanceToZero += Math.abs(r)
    } else {
        distanceToZero = Math.abs(r) + Math.abs(s)
    }
    return distanceToZero
}