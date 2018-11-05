package day9

import java.io.File

fun main(args: Array<String>) {
    var l1 = ""
    var l2 = ""
    var sum1 = 0L
    var sum2 = 0L
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day9/day9src.txt").forEachLine { line ->
        l1 = line
        l2 = line
    }

    //part1
    while (l1.contains("(")) {
        val a = l1.indexOf("(")
        val b = l1.indexOf(")")
        sum1 += a
        val order = l1.substring(a+1, b).split("x").map { it.toInt() }
        sum1 += order[0] * order[1]
        l1 = l1.substring(b + order[0]+1)
    }

    sum1 += l1.length

    println("part1: $sum1")

    val weights = MutableList(l2.length) {1}
    var nextLength = false
    var nextMultiplier = false

    var lenghtAmount = ""
    var multiplierAmount = ""

    l2.forEachIndexed { index, c ->
        if (nextMultiplier) {
            nextMultiplier = false
            nextLength = false
            (0 until lenghtAmount.toInt()).forEach { i->
                weights[index+i] *= multiplierAmount.toInt()
            }
            lenghtAmount = ""
            multiplierAmount = ""
        }
        when {
            c == 'x' -> nextLength = true
            c == ')' -> nextMultiplier = true
            c.toString().contains(Regex("[0-9]")) -> {
                if (nextLength) {
                    multiplierAmount += c
                } else {
                    lenghtAmount += c
                }
            }
            c.toString().contains(Regex("[A-Z]")) -> sum2 += weights[index]
        }
    }

    println("part2: $sum2")
}
