package day24

import java.io.File
import java.util.*

private val labyrinth = mutableListOf<MutableList<Int>>()
private val toCollect = mutableListOf<Int>()
private lateinit var startPosition: Pair<Int, Int>
private val beenHere = mutableListOf<Pair<Int, Int>>()

private val distances = mutableMapOf<Pair<Int, Int>, Int>()
private val positions = mutableMapOf<Int, Pair<Int, Int>>()

fun main(args: Array<String>) {
    val beenHere = mutableListOf<MutableList<Int>>()
    toCollect.add(0)
    File("/Users/david.valic/IdeaProjects/AdventCalendar/AdventCalendar2016/src/day24/day24src.txt").forEachLine { line ->
        labyrinth.add(mutableListOf())
        beenHere.add(mutableListOf())
        line.forEach { c ->
            when (c) {
                '#' -> labyrinth.last().add(-1)
                '.' -> labyrinth.last().add(0)
                '0' -> {
                    labyrinth.last().add(0)
                    startPosition = Pair(labyrinth.lastIndex, labyrinth.last().lastIndex)
                }
                else -> {
                    toCollect.add(c.toString().toInt())
                    labyrinth.last().add(c.toString().toInt())
                    positions[c.toString().toInt()] = Pair(labyrinth.lastIndex, labyrinth.last().lastIndex)
                }
            }
            beenHere.last().add(0)
        }
    }
    positions[0] = startPosition
    (0 until toCollect.size).forEachIndexed { index, first ->
        (index+1 until toCollect.size).forEach { second ->
            distances[Pair(first, second)] = breadth_first_search(positions[first]!!, positions[second]!!)!!
        }
    }
    toCollect.remove(0)
    var distance1 = calcDist(0, toCollect.toMutableList(), distances, false)
    println("part1: $distance1")
    var distance2 = calcDist(0, toCollect.toMutableList(), distances, true)
    println("part1: $distance2")
//    println(distances)

//    breadth_first_search(startPosition)

//    val numberOfSteps1 = tryPaths(startPosition, mutableListOf(), 4)
}

fun calcDist(city: Int, cities: MutableList<Int>, distances: MutableMap<Pair<Int, Int>, Int>, part2: Boolean): Int {

    var distance = Int.MAX_VALUE
    if (cities.size == 1) {
//        println(distances[Pair(0, cities[0])])
        distance = distances[Pair(city, cities[0])]?:distances[Pair(cities[0], city)]?:Int.MAX_VALUE
        if (part2) distance += distances[Pair(0, cities[0])]!!
        return distance
//        return (distances[Pair(city, cities[0])]?:distances[Pair(cities[0], city)]?:Int.MAX_VALUE + distances[Pair(0, cities[0])]!!)
    } else {
        cities.forEach {
            var tmpDistance = 0
            tmpDistance += distances[Pair(city, it)]?:distances[Pair(it, city)]?:Int.MAX_VALUE
            val tmpCities = cities.toMutableList()
            tmpCities.remove(it)
            tmpDistance += calcDist(it, tmpCities, distances, part2)
            if (tmpDistance < distance) {
                distance = tmpDistance
            }
        }

    }
    return distance
}

fun breadth_first_search(startPosition: Pair<Int, Int>, endPosition: Pair<Int, Int>): Int? {


    val open_set = ArrayDeque<Pair<Int, Int>>()

    val closed_set = mutableSetOf<Pair<Int, Int>>()

    val meta = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
    meta[startPosition] = Pair(-1, -1)
    open_set.add(startPosition)
    while (open_set.isNotEmpty()) {


        val subtree_root = open_set.remove()

        if (subtree_root == endPosition) {
            return construct_path(subtree_root, meta)
        }

        var successors = listOf(
            Pair(subtree_root.first+1, subtree_root.second),
            Pair(subtree_root.first, subtree_root.second-1),
            Pair(subtree_root.first-1, subtree_root.second),
            Pair(subtree_root.first, subtree_root.second+1))

        successors = successors.filter { it ->
            labyrinth[it.first][it.second] >= 0
        }

        for (child in successors) {

            if (child in closed_set) continue

            if ((child in open_set).not()) {
                meta[child] = subtree_root //# create metadata for these nodes
                open_set.add(child)              //# enqueue these nodes
            }
        }

        closed_set.add(subtree_root)
    }
    return null
}
fun construct_path(state: Pair<Int, Int>, meta: MutableMap<Pair<Int, Int>, Pair<Int, Int>>): Int {
    var state = state
    val action_list = mutableListOf<Pair<Int, Int>>()

    while (meta[state]?.first != -1 && meta[state]?.second != -1 ) {
        state = meta[state]!!
        val action = meta[state]
        action_list.add(action!!)
    }


//    println(action_list)
//    printMe(action_list)
    return action_list.size

}

fun printMe(action_list: MutableList<Pair<Int, Int>>) {
//    println(currentPosition)
    var toPrint = ""
    labyrinth.forEachIndexed { i, line ->
        line.forEachIndexed { j, l ->
            if (action_list.contains(Pair(i,j))) {
//                print("X")
                toPrint += "X"
            } else {
                toPrint += when (l) {
                    -1 -> {
//                        print("#")
                         "#"
                    }
                    0 -> {
//                        print(".")
                         "."
                    }
                    else -> {
//                        print(l)
                         l
                    }
                }
            }
        }
//        println()
        toPrint += "\n"
    }
    toPrint += "\n"
    print(toPrint)
}





fun tryPaths(currentPosition: Pair<Int, Int>, collected: MutableList<Int>, lastDirection: Int): Int {
    var minSteps = Int.MAX_VALUE-200

//    var beenHere = beenHereIn.toMutableList()
//    val currentValue = labyrinth[currentPosition.first][currentPosition.second]
//    var justCollected = false
//    if (currentValue > 0 && collected.contains(currentValue).not()) {
//        collected.add(currentValue)
//        justCollected = true
//        beenHere = mutableListOf()
//    }

//    if (collected.size == toCollect.size) {
//        return 0
//    }
//
//    if (collected.size < toCollect.size) {
    if (labyrinth[currentPosition.first+1][currentPosition.second] == 5 ) {
        return 0
    }
    beenHere.add(currentPosition)
//        UP 1
    if (labyrinth[currentPosition.first+1][currentPosition.second] >= 0 && (lastDirection != 3 ) && beenHere.contains(Pair(currentPosition.first+1, currentPosition.second)).not()) {
        var tmpSteps = 1
        printMe(Pair(currentPosition.first+1, currentPosition.second))
//            beenHere.add(Pair(currentPosition.first+1, currentPosition.second))
        tmpSteps += tryPaths(Pair(currentPosition.first+1, currentPosition.second), collected.toMutableList(), 1)
        if (tmpSteps < minSteps) {
            minSteps = tmpSteps
            return minSteps
        }
//            beenHere.remove(Pair(currentPosition.first+1, currentPosition.second))
    }
    //RIGHT 2
    if (labyrinth[currentPosition.first][currentPosition.second+1] >= 0 && (lastDirection != 4 ) && beenHere.contains(Pair(currentPosition.first, currentPosition.second+1)).not()) {
        var tmpSteps = 1
        printMe(Pair(currentPosition.first, currentPosition.second+1))
//            beenHere.add(Pair(currentPosition.first, currentPosition.second+1))
        tmpSteps += tryPaths(Pair(currentPosition.first, currentPosition.second+1), collected.toMutableList(), 2)
        if (tmpSteps < minSteps) {
            minSteps = tmpSteps
            return minSteps
        }
//            beenHere.remove(Pair(currentPosition.first, currentPosition.second+1))
    }
    //DOWN 3
    if (labyrinth[currentPosition.first-1][currentPosition.second] >= 0 && (lastDirection != 1 ) && beenHere.contains(Pair(currentPosition.first-1, currentPosition.second)).not()) {
        var tmpSteps = 1
        printMe(Pair(currentPosition.first-1, currentPosition.second))
//            beenHere.add(Pair(currentPosition.first-1, currentPosition.second))
        tmpSteps += tryPaths(Pair(currentPosition.first-1, currentPosition.second), collected.toMutableList(), 3)
        if (tmpSteps < minSteps) {
            minSteps = tmpSteps
            return minSteps
        }
//            beenHere.remove(Pair(currentPosition.first-1, currentPosition.second))
    }
    //LEFT 4
    if (labyrinth[currentPosition.first][currentPosition.second-1] >= 0 && (lastDirection != 2 ) && beenHere.contains(Pair(currentPosition.first, currentPosition.second-1)).not()) {
        var tmpSteps = 1
        printMe(Pair(currentPosition.first, currentPosition.second-1))
//            beenHere.add(Pair(currentPosition.first, currentPosition.second-1))
        tmpSteps += tryPaths(Pair(currentPosition.first, currentPosition.second-1), collected.toMutableList(), 4)
        if (tmpSteps < minSteps) {
            minSteps = tmpSteps
            return minSteps
        }
//            beenHere.remove(Pair(currentPosition.first, currentPosition.second-1))
    }
//    }
    beenHere.remove(currentPosition)

    return minSteps
}

fun printMe(currentPosition: Pair<Int, Int>) {
    var toPrint = "\n\n$currentPosition\n"
//    println(currentPosition)
    labyrinth.forEachIndexed { i, line ->
        line.forEachIndexed { j, l ->
            if (currentPosition.first == i && currentPosition.second == j) {
//                print("X")
                toPrint += "X"
            } else {
                when (l) {
                    -1 -> {
//                        print("#")
                        toPrint += "#"
                    }
                    0 -> {
//                        print(".")
                        toPrint += "."
                    }
                    else -> {
//                        print(l)
                        toPrint += l
                    }
                }
            }
        }
//        println()
        toPrint += "\n"
    }
    toPrint += "\n"
//    print(toPrint)
}