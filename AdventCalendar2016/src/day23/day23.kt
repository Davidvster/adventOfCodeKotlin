package day23

import java.io.File

fun main(args: Array<String>) {
    //part1
    var values1 = mutableMapOf<Char, Int>(
            'a' to 7
    )
    var values2 = mutableMapOf<Char, Int>(
            'a' to 12
    )
    val instructions1 = mutableListOf<MutableList<String>>()
    val instructions2 = mutableListOf<MutableList<String>>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day23/day23src.txt").forEachLine { line ->
        instructions1.add(line.split(" ").toMutableList())
        instructions2.add(line.split(" ").toMutableList())
    }

    values1 = processInstructions(instructions1, values1)
    println("part1: ${values1['a']}")
    values2 = processInstructions(instructions2, values2)
    println("part2: ${values2['a']}")

}

fun processInstructions(orders: MutableList<MutableList<String>>, values: MutableMap<Char, Int>): MutableMap<Char, Int> {
    var i = 0
    while (i < orders.size) {
        when (orders[i][0]) {
            "tgl" -> {
                if (values[orders[i][1].single()]!! + i < orders.size) {
                    val j = i+ values[orders[i][1].single()]!!
                    orders[j][0] = when {
                        orders[j].contains("inc") -> "dec"
                        orders[j].size == 2 -> "inc"
                        orders[j].contains("jnz") -> "cpy"
                        else -> "jnz"
                    }
                }
                i++
            }
            "cpy" -> {
                if (orders[i][1].toIntOrNull() != null) {
                    values[orders[i][2].single()] = orders[i][1].toInt()
                } else if (orders[i][2][0].isLetter()){
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
                if ((orders[i][1].toIntOrNull() != null && orders[i][1].toInt() != 0) || values[orders[i][1].single()] != 0) {
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
    return values
}
