package day9

import java.io.File

fun main(args: Array<String>) {
    var score1 = 0
    var score2 = 0
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2017/src/day9/day9src.txt").forEachLine { line ->
        val l = line.toCharArray()
        var inGroup = 0
        var inGarbage = false
        l.forEachIndexed { index, c ->
            when {
                c == '!' -> {
                    l[index+1] = '_'
                }
                inGarbage -> {
                    if (c == '>') {
                        inGarbage = false
                    } else if (c != '_'){
                        score2 ++
                    }
                }
                c == '<' -> {
                    inGarbage = true
                }
                c == '>' -> {
                    inGarbage = false
                }
                c == '{' -> {
                    inGroup ++
                    score1 += inGroup
                }
                c == '}' -> {
                    inGroup --
                }
            }
        }
    }

    println("part1: $score1")
    println("part2: $score2")
}