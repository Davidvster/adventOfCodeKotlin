package day3

fun main(args: Array<String>) {
    val puzzle = 265149

    //part1
    var circleIndex = 0
    var number = 1
    var finalNumber = 0
    while (number < puzzle) {
        finalNumber = number
        number += circleIndex * 8
        if (number < puzzle) {
            circleIndex ++
        }
    }
    var steps1 = 0
    val toPuzzle = puzzle - finalNumber
    when (toPuzzle/(circleIndex*2)) {
        0 -> {
            val x = circleIndex
            val y = circleIndex - toPuzzle
            steps1 = Math.abs(x) + Math.abs(y)
        }
        1 -> {
            val x = circleIndex - toPuzzle %(circleIndex*2)
            val y = -circleIndex
            steps1 = Math.abs(x) + Math.abs(y)
        }
        2 -> {
            val x = -circleIndex
            val y = -circleIndex + toPuzzle %(circleIndex*2)
            steps1 = Math.abs(x) + Math.abs(y)
        }
        3 -> {
            val x = -circleIndex + toPuzzle %(circleIndex*2)
            val y = circleIndex
            steps1 = Math.abs(x) + Math.abs(y)
        }
    }

    println("part1: $steps1")

    //part2
    var x = 0
    var y = 0
    var currentNumber = 1
    val grid = mutableMapOf<Pair<Int, Int>, Int>()
    var direction = 0
    while (currentNumber < puzzle) {
        grid[Pair(x,y)] = currentNumber
        if ((Math.abs(x) == Math.abs(y) && direction != 1) || (x == y+1 && direction == 1) ) {
            direction = direction.nextDirection()
        }
        when (direction) {
            1 -> x ++
            2 -> y --
            3 -> x --
            4 -> y ++
        }

        currentNumber = grid.getOrDefault(Pair(x-1,y), 0) +
                grid.getOrDefault(Pair(x-1,y-1), 0) +
                grid.getOrDefault(Pair(x,y-1), 0) +
                grid.getOrDefault(Pair(x+1,y-1), 0) +
                grid.getOrDefault(Pair(x+1,y), 0) +
                grid.getOrDefault(Pair(x+1,y+1), 0) +
                grid.getOrDefault(Pair(x,y+1), 0) +
                grid.getOrDefault(Pair(x-1,y+1), 0)

    }

    println("part2: $currentNumber")
}

fun Int.nextDirection() = if (this == 4) 1 else this + 1