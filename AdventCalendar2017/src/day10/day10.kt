package day10

import java.io.File

fun main(args: Array<String>) {
    var output2 = ""
    var list1 = mutableListOf<Int>()
    var list2 = mutableListOf<Int>()
    (0 .. 255).forEach {
        list1.add(it)
        list2.add(it)
    }
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2017/src/day10/day10src.txt").forEachLine { line ->
        //part1
        val lengths = line.split(",").map { it.toInt() }
        list1 = knotHash(lengths, list1.toMutableList(), 1)

        //part2
        val lengths2 = mutableListOf<Int>()
        line.forEach {
            if (it != ' ')
            lengths2.add(it.toInt())
        }
        lengths2.addAll(listOf(17, 31, 73, 47, 23))
        list2 = knotHash(lengths2, list2.toMutableList(), 64)
        val densedHash = mutableListOf<Int>()
        (0 until 16).forEach {
            var densed = 0
            val toDesnse = list2.subList(it*16, (it+1)*16)
            toDesnse.forEach {
                densed = densed xor it
            }
            densedHash.add(densed)
            output2 += densed.toString(16)
        }


    }
    val multiplied1 = list1[0] * list1[1]
    println("part1: $multiplied1")
    println("part2: $output2")
}

fun knotHash(lengths: List<Int>, list: MutableList<Int>, steps: Int): MutableList<Int> {
    var currentPosition = 0
    var skipSize = 0
    (0 until steps).forEach {
        lengths.forEach { length ->
            val reversed = mutableListOf<Int>()
            var reversing = length-1
            while (reversing >= 0) {
                if (currentPosition + reversing >= list.size) {
                    reversed.add(list[(currentPosition + reversing) % list.size])
                } else {
                    reversed.add(list[currentPosition + reversing])
                }
                reversing--
            }
            reversing = 0
            while (reversing < length) {
                if (currentPosition + reversing >= list.size) {
                    list[(currentPosition + reversing) % list.size] = reversed.removeAt(0)
                } else {
                    list[currentPosition + reversing] = reversed.removeAt(0)
                }
                reversing++
            }
            currentPosition += length + skipSize
            if (currentPosition >= list.size) currentPosition %= list.size
            skipSize ++
        }
    }
    return list
}