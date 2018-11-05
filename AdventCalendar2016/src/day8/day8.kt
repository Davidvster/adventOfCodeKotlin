package day8

import java.io.File

private const val displayWidth = 50
private const val displayHeight = 6

fun main(args: Array<String>) {
    val display = List(displayHeight) { MutableList(displayWidth) {0} }

    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day8/day8src.txt").forEachLine { line ->
        val l = line.split(" ")
        when {
            l.contains("rect") -> {
                val coordinates = l[1].split("x")
                (0 until coordinates[0].toInt()).forEach { x ->
                    (0 until coordinates[1].toInt()).forEach { y ->
                        display[y][x] = 1
                    }
                }
            }
            //rotate row
            l.contains("column") -> {
                val which = l[2].substring(2).toInt()
                val amount = l[4].toInt()
                val newLine = MutableList(displayHeight) {0}
                display.forEachIndexed { index, _ ->
                    newLine[(index+amount)% displayHeight] = display[index][which]
                }
                newLine.forEachIndexed { index, b ->
                    display[index][which] = b
                }
            }
            //rotate column
            l.contains("row") -> {
                val which = l[2].substring(2).toInt()
                val amount = l[4].toInt()
                val newColumn = MutableList(displayWidth) {0}
                display[which].forEachIndexed { index, _ ->
                    newColumn[(index+amount)% displayWidth] = display[which][index]
                }
                newColumn.forEachIndexed { index, b ->
                    display[which][index] = b
                }
            }
        }

    }

    val lightsOn1 = display.sumBy { it.sumBy { it } }
    println("part1: $lightsOn1")

    //part2
    display.forEach { column ->
        column.forEach {
            if (it == 1) print("#")
            else print(".")
        }
        println()
    }
    println()
}