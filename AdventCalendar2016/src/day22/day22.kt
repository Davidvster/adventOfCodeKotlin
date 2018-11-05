package day22

import java.io.File

fun main(args: Array<String>) {
    val x = 33
    val y = 31
    var orders = mutableListOf<List<String>>()
    val nodes = mutableListOf<MutableList<Triple<Int, Int, Int>>>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day22/day22src.txt").forEachLine { line ->
        orders.add(line.split(" ").filter { it.isNotEmpty() })
    }
    orders = orders.subList(2, orders.size)
    var curX = -1
    orders.forEachIndexed { index, list ->
        if (index%y == 0) {
            curX++
            nodes.add(mutableListOf())
        }
        nodes[curX].add(Triple(list[1].substring(0, list[1].length-1).toInt(), list[2].substring(0, list[2].length-1).toInt(), list[3].substring(0, list[3].length-1).toInt()))
    }
    var viablePairs = 0
    var freeNode = -1
    for (i in 0 until x) {
        for (j in 0 until y) {
            if (nodes[i][j].second == 0) {
                freeNode = nodes[i][j].third
            }
            if (nodes[i][j].second > 0) {
                for (k in 0 until x) {
                    for (l in 0 until y) {
                        if (nodes[i][j].second <= nodes[k][l].third) {
                            viablePairs++
                        }
                    }
                }
            }
        }
    }

    println("part1: $viablePairs")

    //part2 63 + 31 * 5
    for (i in 0 until x) {
        for (j in 0 until y) {
            if (i == 0 && j == 0) {
                print("(.)")
            }
            else if (i == 32 && j == 0) {
                print(" G ")
            }
            else if (nodes[i][j].second == 0) {
                print(" - ")
            }
            else if (nodes[i][j].second > freeNode) {
                print(" # ")
            } else {
                print(" . ")
            }
        }
        println()
    }


}