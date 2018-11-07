package day25

import java.io.File

private const val patternLength = 1000

fun main(args: Array<String>) {
    //part1
    var values1 = mutableMapOf<Char, Int>(
            'a' to -1
    )
    val instructions = mutableListOf<List<String>>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day25/day25src.txt").forEachLine { line ->
        instructions.add(line.split(" "))
    }
    var found = false
    while(found.not()) {
        values1['a'] = values1['a']!! + 1
        found = processInstructions(instructions, values1.toMutableMap())
//        println(values1['a'])
    }

    println("part1: ${values1['a']}")
}

fun processInstructions(orders: MutableList<List<String>>, values: MutableMap<Char, Int>): Boolean {
    var i = 0
    var out = ""
    while (i < orders.size) {
        when (orders[i][0]) {
            "out" -> {
                out += values[orders[i][1].single()]
                i++
                if (out.length >= patternLength) {
                    return out.split("01").any { it.contains(Regex("[0-9]"))}.not()
                }
            }
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
                if ((orders[i][1].toIntOrNull() != null && orders[i][1].toInt() != 0) || (values[orders[i][1].single()] != null && values[orders[i][1].single()] != 0)) {
                    i+= when (orders[i][2].toIntOrNull() != null ) {
                        true -> orders[i][2].toInt()
                        false ->  values[orders[i][2].single()]!!
                    }
                } else {
                    i++
                }
            }
        }
    }
    return false
}
