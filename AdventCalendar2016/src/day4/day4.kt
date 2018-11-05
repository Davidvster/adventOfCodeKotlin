package day4

import java.io.File

fun main(args: Array<String>) {
    var ids1 = 0
    var characters = mutableMapOf<Char, Int>()
    val codeWords = mutableListOf<String>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day4/day4src.txt").forEachLine { line ->
        val l = line.split("-")
        val check = l.last().split("[")
        val checksum = check[1].substring(0, check[1].length-1)
        val roomId = check[0].toInt()
        val rotation = roomId % 26
        var codeWord = ""

        l.subList(0, l.size-1).forEach { code ->
            code.forEach { c ->
                //part1
                characters[c] = (characters[c]?:0) + 1
                //part2
                var codeChar = (c.toInt() + rotation).toChar()
                if (codeChar.toInt() > 122) {
                    codeChar = (96 + codeChar.toInt() - 122).toChar()
                }
                codeWord += codeChar.toString()
            }
            codeWord += " "
        }

        codeWords.add("$codeWord - $roomId")

        //part1
        val chars = characters.toList().sortedByDescending { (_, value) -> value }
        var isARoom = true
        checksum.forEachIndexed { i, c ->
            if (characters[c] == null || characters[c] != chars[i].second ) {
                isARoom = false
            }
        }
        if (isARoom) {
            ids1 += roomId
        }
        characters = mutableMapOf()
    }
    println("part1: $ids1")
    codeWords.filter { it.contains("northpole object storage") }.forEach {
        println("part2: $it")
    }
}
