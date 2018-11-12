package day2

import java.io.File

fun main(args: Array<String>) {
    var sum1 = 0
    var sum2 = 0
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2017/src/day2/day2src.txt").forEachLine { line ->
        var l = line.split(Regex("\t")).map { it.toInt() }
        sum1 += (l.max()?:0) - (l.min()?:0)
        l = l.sorted()
        sum2 += (0 until l.size-1).map { first ->
            (first+1 until l.size).map { second ->
                if (l[second] %l[first] == 0) l[second]/l[first] else 0
            }
        }.sumBy { it.sumBy { it } }
    }
    println("part1: $sum1")
    println("part2: $sum2")
}