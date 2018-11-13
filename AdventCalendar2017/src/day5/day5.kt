package day5

import java.io.File

fun main(args: Array<String>) {
    var steps1 = 0
    var steps2 = 0
    val orders = mutableListOf<Int>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2017/src/day5/day5src.txt").forEachLine { line ->
        orders.add(line.toInt())
    }
    val orders1 = orders.toMutableList()
    var currentPosition = 0
    while (currentPosition < orders1.size) {
        val tmpCurrentPos = currentPosition
        currentPosition += orders1[currentPosition]
        orders1[tmpCurrentPos] ++
        steps1++
    }
    println("part1: $steps1")

    //part2
    val orders2 = orders.toMutableList()
    currentPosition = 0
    while (currentPosition < orders.size) {
        val tmpCurrentPos = currentPosition
        currentPosition += orders2[currentPosition]
        if (orders2[tmpCurrentPos] >= 3) orders2[tmpCurrentPos]-- else orders2[tmpCurrentPos]++
        steps2++
    }
    println("part2: $steps2")
}