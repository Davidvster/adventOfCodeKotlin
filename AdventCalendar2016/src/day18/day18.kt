package day18

import java.io.File

fun main(args: Array<String>) {

    var currentLine = mutableListOf<Boolean>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day18/day18src.txt").forEachLine { line ->
        line.forEach {
            if (it == '.') {
                currentLine.add(false)
            } else {
                currentLine.add(true)
            }
        }
    }

    var safeTiles1 = 0
    var currentLine1 = currentLine.toMutableList()
    (1 .. 40).forEach {
        val newLine = mutableListOf<Boolean>()
        currentLine.forEachIndexed { index, b ->
            if (((currentLine1.elementAtOrNull(index-1)?:false) && (currentLine1.elementAtOrNull(index)?:false) && !(currentLine1.elementAtOrNull(index+1)?:false)) ||
                    (!(currentLine1.elementAtOrNull(index-1)?:false) && (currentLine1.elementAtOrNull(index)?:false) && (currentLine1.elementAtOrNull(index+1)?:false)) ||
                    ((currentLine1.elementAtOrNull(index-1)?:false) && !(currentLine1.elementAtOrNull(index)?:false) && !(currentLine1.elementAtOrNull(index+1)?:false)) ||
                    (!(currentLine1.elementAtOrNull(index-1)?:false) && !(currentLine1.elementAtOrNull(index)?:false) && (currentLine1.elementAtOrNull(index+1)?:false))) {
                newLine.add(true)
            } else {
                newLine.add(false)
            }
        }
        safeTiles1 += currentLine1.count { it.not() }
        currentLine1 = newLine.toMutableList()
    }
    println("part1: $safeTiles1")

    var safeTiles2 = 0
    var currentLine2 = currentLine.toMutableList()
    (1 .. 400000).forEach {
        val newLine = mutableListOf<Boolean>()
        currentLine.forEachIndexed { index, b ->
            if (((currentLine2.elementAtOrNull(index-1)?:false) && (currentLine2.elementAtOrNull(index)?:false) && !(currentLine2.elementAtOrNull(index+1)?:false)) ||
                    (!(currentLine2.elementAtOrNull(index-1)?:false) && (currentLine2.elementAtOrNull(index)?:false) && (currentLine2.elementAtOrNull(index+1)?:false)) ||
                    ((currentLine2.elementAtOrNull(index-1)?:false) && !(currentLine2.elementAtOrNull(index)?:false) && !(currentLine2.elementAtOrNull(index+1)?:false)) ||
                    (!(currentLine2.elementAtOrNull(index-1)?:false) && !(currentLine2.elementAtOrNull(index)?:false) && (currentLine2.elementAtOrNull(index+1)?:false))) {
                newLine.add(true)
            } else {
                newLine.add(false)
            }
        }
        safeTiles2 += currentLine2.count { it.not() }
        currentLine2 = newLine.toMutableList()
    }
    println("part2: $safeTiles2")
}