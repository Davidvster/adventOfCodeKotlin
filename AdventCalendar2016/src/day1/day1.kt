package day1

import java.io.File

var exploredLocations = mutableListOf<Pair<Int, Int>>()
var part2x = 0
var part2y = 0
var found = false

fun main(args: Array<String>) {
    var x = 0
    var y = 0
    var direction = 0

    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day1/day1src.txt").forEachLine { line ->
        val l = line.replace(",", "").split(" ")
        l.forEach { order ->
            when (order[0]) {
                'R' -> {
                    direction ++
                    if (direction == 4) direction = 0
                }
                'L' -> {
                    direction --
                    if (direction == -1) direction = 3
                }
            }
            when (direction) {
                0 -> {
                    (1 .. order.substring(1).toInt()).forEach {
                        y ++
                        if (!found) checkLocation(x, y)
                    }
                }
                1 -> (1 .. order.substring(1).toInt()).forEach {
                    x ++
                    if (!found) checkLocation(x, y)
                }
                2 -> (1 .. order.substring(1).toInt()).forEach {
                    y --
                    if (!found) checkLocation(x, y)
                }
                3 -> (1 .. order.substring(1).toInt()).forEach {
                    x --
                    if (!found) checkLocation(x, y)
                }
            }

        }
    }

    val distance1 = Math.abs(x) + Math.abs(y)
    println("part1: $distance1")

    val distance2 = Math.abs(part2x) + Math.abs(part2y)
    println("part2 $distance2")
}

fun checkLocation(x: Int, y: Int) {
    if (exploredLocations.contains(Pair(x, y)) && !found) {
        found = true
        part2x = x
        part2y = y
    } else if (!found) {
        exploredLocations.add(Pair(x, y))
    }
}