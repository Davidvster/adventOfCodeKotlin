package day6

import java.io.File

fun main(args: Array<String>) {
    var steps1 = 0
    var cycles2 = 0
    val blocks = mutableListOf<Int>()
    val savedConfigurations = mutableListOf<MutableList<Int>>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2017/src/day6/day6src.txt").forEachLine { line ->
        line.split(Regex("\t")).forEach {
            blocks.add(it.toInt())
        }
    }
    var repeated = false
    while (repeated.not()) {
        steps1++
        var toDistribute = blocks.max()?:0
        var toDistributeIndex = blocks.indexOf(toDistribute)
        blocks[toDistributeIndex] = 0
        while (toDistribute > 0) {
            toDistributeIndex++
            if (toDistributeIndex == blocks.size) toDistributeIndex = 0
            blocks[toDistributeIndex] ++
            toDistribute --
        }
        if (savedConfigurations.contains(blocks)) {
            repeated = true
        } else {
            savedConfigurations.add(blocks.toMutableList())
        }
    }

    cycles2 = savedConfigurations.size - savedConfigurations.indexOf(blocks)
    println("part1: $steps1")
    println("part2: $cycles2")
}