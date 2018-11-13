package day4

import java.io.File

fun main(args: Array<String>) {
    var valid1 = 0
    var valid2 = 0
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2017/src/day4/day4src.txt").forEachLine { line ->
        val l = line.split(" ")
        if (l.distinct().size == l.size) valid1++
        val chars = mutableListOf<List<Char>>()
        l.forEach {
            chars.add(it.toCharArray().sorted())
        }
        if (chars.distinct().size == chars.size) valid2++

    }
    println("part1: $valid1")
    println("part2: $valid2")
}