package day12

import java.io.File

fun main(args: Array<String>) {
    //part1
    var values1 = mutableMapOf<Char, Int>(
            'a' to 0,
            'b' to 0,
            'c' to 0,
            'd' to 0
    )
    //part2
    var values2 = mutableMapOf<Char, Int>(
            'a' to 0,
            'b' to 0,
            'c' to 1,
            'd' to 0
    )
    val instructions = mutableListOf<List<String>>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day12/day12src.txt").forEachLine { line ->
        instructions.add(line.split(" "))
    }

    values1 = processInstructions(instructions, values1)
    println("part1: ${values1['a']}")

    values2 = processInstructions(instructions, values2)
    println("part2: ${values2['a']}")
}

fun processInstructions(orders: MutableList<List<String>>, values: MutableMap<Char, Int>): MutableMap<Char, Int> {
    var i = 0
    while (i < orders.size) {
        when (orders[i][0]) {
            "cpy" -> {
                if (orders[i][1][0].isDigit()) {
                    values[orders[i][2].single()] = orders[i][1].toInt()
                } else {
                    values[orders[i][2].single()] = values[orders[i][1].single()]?:0
                }
                i++
            }
            "inc" -> {
                values[orders[i][1].single()] = values[orders[i][1].single()]!! + 1
                i++
            }
            "dec" -> {
                values[orders[i][1].single()] = values[orders[i][1].single()]!! - 1
                i++
            }
            "jnz" -> {
                if (values[orders[i][1].single()] != 0) {
                    i += orders[i][2].toInt()
                } else {
                    i++
                }
            }
        }
    }
    return values
}
