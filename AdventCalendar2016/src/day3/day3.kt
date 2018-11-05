package day3

import java.io.File

fun main(args: Array<String>) {
    var validTriangles1 = 0
    var validTriangles2 = 0
    var first = mutableListOf<Int>()
    var second = mutableListOf<Int>()
    var third = mutableListOf<Int>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day3/day3src.txt").forEachLine { line ->
        var triangle = line.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
        //part1
        validTriangles1 += checkValid(triangle.sorted())
        //part2
        first.add(triangle[0])
        second.add(triangle[1])
        third.add(triangle[2])
        if (first.size == 3) {
            validTriangles2 += checkValid(first.sorted())
            validTriangles2 += checkValid(second.sorted())
            validTriangles2 += checkValid(third.sorted())
            first = mutableListOf()
            second = mutableListOf()
            third = mutableListOf()
        }
    }
    println("part1: $validTriangles1")
    println("part2: $validTriangles2")
}

fun checkValid(triangle: List<Int>): Int {
    if (triangle[0] + triangle[1] > triangle[2]) {
        return 1
    }
    return 0
}
