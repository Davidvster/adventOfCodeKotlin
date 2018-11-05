package day2

import java.io.File

fun main(args: Array<String>) {
    //part1
    val pinCode1 = mutableListOf<Int>()
    var start = 5
    //part2
    val pinPad = listOf(
            listOf(0,0,1,0,0),
            listOf(0,2,3,4,0),
            listOf(5,6,7,8,9),
            listOf(0,'A','B','C',0),
            listOf(0,0,'D',0,0)
    )
    var x = 0
    var y = 2
    val pinCode2 = mutableListOf<Any>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day2/day2src.txt").forEachLine { line ->
        line.forEach { direction ->
            //part1
            when (direction) {
                'U' -> if (start > 3) start -= 3
                'D' -> if (start < 7) start += 3
                'R' -> if (start %3 != 0) start ++
                'L' -> if ((start - 1) %3 != 0) start --
            }
            //part2
            when (direction) {
                'D' -> if (y+1 <= 4 && pinPad[y+1][x] != 0) y ++
                'U' -> if (y-1 >= 0 && pinPad[y-1][x] != 0) y --
                'R' -> if (x+1 <= 4 && pinPad[y][x+1] != 0) x ++
                'L' -> if (x-1 >= 0 && pinPad[y][x-1] != 0) x --
            }
        }
        pinCode1.add(start)
        pinCode2.add(pinPad[y][x])
    }
    println("part1: ")
    pinCode1.forEach { print(it) }
    println()
    println("part2: ")
    pinCode2.forEach { print(it) }
}
