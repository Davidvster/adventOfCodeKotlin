package day21

import java.io.File

fun main(args: Array<String>) {
    var puzzle1 = "abcdefgh".toMutableList()
    var puzzle2 = "fbgdceah".toMutableList()
    val orders = mutableListOf<List<String>>()
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day21/day21src.txt").forEachLine { line ->
        orders.add(line.split(" "))

    }

    orders.forEach { l ->
        when {
            l.contains("swap") && l.contains("position") -> {
                puzzle1.swapPosition(l[2].toInt(), l[5].toInt())
            }
            l.contains("swap") && l.contains("letter") -> {
                puzzle1.swapLetter(l[2], l[5])
            }
            l.contains("rotate") && l.contains("position") -> {
                var rotationAmount1 = puzzle1.indexOf(l[6].single())
                rotationAmount1 += when (rotationAmount1 >= 4)  {
                    true -> 2
                    false -> 1
                }
                puzzle1 = rotateRight(puzzle1, rotationAmount1)
            }
            l.contains("rotate") -> {
                puzzle1 = when(l.contains("left")) {
                    true -> rotateLeft(puzzle1, l[2].toInt())
                    false -> rotateRight(puzzle1, l[2].toInt())
                }
            }
            l.contains("reverse") -> {
                puzzle1.doReverse(l[2].toInt(), l[4].toInt())
            }
            l.contains("move") -> {
                puzzle1.doMove(l[2].toInt(), l[5].toInt())
            }
        }
    }

    println("part1: ${puzzle1.joinToString("")}")

    orders.reverse()
    orders.forEach { l ->
        when {
            l.contains("swap") && l.contains("position") -> {
                puzzle2.swapPosition(l[2].toInt(), l[5].toInt())
            }
            l.contains("swap") && l.contains("letter") -> {
                puzzle2.swapLetter(l[2], l[5])
            }
            l.contains("rotate") && l.contains("position") -> {
                var solution = -1
                for (i in 0 until puzzle2.size) {
                    var puzzleToCheck = rotateLeft(puzzle2, i)
                    var rotationAmount2 = puzzleToCheck.indexOf(l[6].single())
                    rotationAmount2 += when (rotationAmount2 >= 4)  {
                        true -> 2
                        false -> 1
                    }
                    puzzleToCheck = rotateRight(puzzleToCheck, rotationAmount2)
                    if (puzzleToCheck == puzzle2) {
                        solution = i
                    }
                }
                puzzle2 = rotateLeft(puzzle2, solution)
            }
            l.contains("rotate") -> {
                puzzle2 = when(l.contains("left")) {
                    true -> rotateRight(puzzle2, l[2].toInt())
                    false -> rotateLeft(puzzle2, l[2].toInt())
                }
            }
            l.contains("reverse") -> {
                puzzle2.doReverse(l[2].toInt(), l[4].toInt())
            }
            l.contains("move") -> {
                puzzle2.doMove(l[5].toInt(), l[2].toInt())
            }
        }
    }

    println("part2: ${puzzle2.joinToString("")}")
}

fun rotateLeft(l: MutableList<Char>, amount: Int): MutableList<Char> {
    val rotated = MutableList(l.size) {'_'}
    for (i in 0 until l.size) {
        rotated[i] = l[Math.abs((i + amount) % l.size)]
    }
    return rotated
}

fun rotateRight(l: MutableList<Char>, amount: Int): MutableList<Char> {
    val rotated = MutableList(l.size) {'_'}
    for (i in 0 until l.size) {
        var newPosition = (i - amount) % l.size
        if (newPosition < 0) {
            newPosition += l.size
        }
        rotated[i] = l[newPosition]
    }
    return rotated
}

fun MutableList<Char>.swapPosition( p1: Int, p2: Int) {
    val s = this[p1]
    this[p1] = this[p2]
    this[p2] = s
}

fun MutableList<Char>.swapLetter(l1: String, l2: String){
    val puzzleTmp = this.joinToString("").replace(l1, "_").toMutableList()
    this.removeAll(this)
    this.addAll(puzzleTmp.joinToString("").replace(l2, l1).replace("_", l2).toMutableList())
}

fun MutableList<Char>.doReverse( p1: Int, p2: Int) {
    val reversed = this.subList(p1, p2+1).reversed()
    this.subList(p1, p2+1).clear()
    this.addAll(p1, reversed)
}

fun MutableList<Char>.doMove(p1: Int, p2: Int) {
    val toInsert = this[p1]
    this.removeAt(p1)
    this.add(p2, toInsert)
}