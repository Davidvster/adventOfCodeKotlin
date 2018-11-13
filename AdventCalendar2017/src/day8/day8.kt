package day8

import java.io.File

fun main(args: Array<String>) {
    var max2 = Int.MIN_VALUE
    val registers = mutableMapOf<String, Int>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2017/src/day8/day8src.txt").forEachLine { line ->
        val l = line.split(" ")
        when (l[5]) {
            ">" -> {
                if (registers.getOrDefault(l[4], 0) > l[6].toInt()){
                    registers[l[0]] = if (l[1] == "inc") registers.getOrDefault(l[0], 0) + l[2].toInt() else registers.getOrDefault(l[0], 0) - l[2].toInt()
                }
            }
            "<" -> {
                if (registers.getOrDefault(l[4], 0) < l[6].toInt()){
                    registers[l[0]] = if (l[1] == "inc") registers.getOrDefault(l[0], 0) + l[2].toInt() else registers.getOrDefault(l[0], 0) - l[2].toInt()
                }
            }
            "<=" -> {
                if (registers.getOrDefault(l[4], 0) <= l[6].toInt()){
                    registers[l[0]] = if (l[1] == "inc") registers.getOrDefault(l[0], 0) + l[2].toInt() else registers.getOrDefault(l[0], 0) - l[2].toInt()
                }
            }
            ">=" -> {
                if (registers.getOrDefault(l[4], 0) >= l[6].toInt()){
                    registers[l[0]] = if (l[1] == "inc") registers.getOrDefault(l[0], 0) + l[2].toInt() else registers.getOrDefault(l[0], 0) - l[2].toInt()
                }
            }
            "==" -> {
                if (registers.getOrDefault(l[4], 0) == l[6].toInt()){
                    registers[l[0]] = if (l[1] == "inc") registers.getOrDefault(l[0], 0) + l[2].toInt() else registers.getOrDefault(l[0], 0) - l[2].toInt()
                }
            }
            "!=" -> {
                if (registers.getOrDefault(l[4], 0) != l[6].toInt()){
                    registers[l[0]] = if (l[1] == "inc") registers.getOrDefault(l[0], 0) + l[2].toInt() else registers.getOrDefault(l[0], 0) - l[2].toInt()
                }
            }
        }
        if (registers.maxBy { it.value }?.value?:Int.MIN_VALUE > max2) {
            max2 = registers.maxBy { it.value }?.value?:Int.MIN_VALUE
        }
    }
    println("part1: ${registers.maxBy { it.value }}")
    println("part2: $max2")
}